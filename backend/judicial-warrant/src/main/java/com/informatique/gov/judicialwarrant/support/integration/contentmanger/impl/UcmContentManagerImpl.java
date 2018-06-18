package com.informatique.gov.judicialwarrant.support.integration.contentmanger.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.informatique.gov.judicialwarrant.support.integration.contentmanger.ContentManager;

import oracle.stellent.ridc.IdcClient;
import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.IdcClientManager;
import oracle.stellent.ridc.IdcContext;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.model.DataObject;
import oracle.stellent.ridc.model.DataResultSet;
import oracle.stellent.ridc.model.TransferFile;
import oracle.stellent.ridc.protocol.ServiceResponse;

@Component
public class UcmContentManagerImpl implements ContentManager {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2157284910174686034L;
	private final String ip, port, username;
	private final char[] password;
	private final static String DOCUMENT_TYPE = "Application";
	private final static String SECURITY_GROUP = "Public";

	public UcmContentManagerImpl(Environment environment) {

		this.ip = environment.getProperty("ucm.ip");
		this.port = environment.getProperty("ucm.port");
		this.username = environment.getProperty("ucm.username");
		this.password = environment.getProperty("ucm.password").toCharArray();
	}

	private IdcClient<?, ?, ?> getIdcClient(String ip, String port) throws IdcClientException {

		IdcClientManager idcClientManager = new IdcClientManager();
		IdcClient<?, ?, ?> idcClient = idcClientManager.createClient("idc://" + ip + ":" + port);
		return idcClient;

	}

	@Override
	public String checkin(Map<String, String> properties, File file) throws Exception {

		String contentId = null;
		ServiceResponse serviceResponse = null;

		try {

			InputStream is = new FileInputStream(file);
			IdcClient<?, ?, ?> idcClient = getIdcClient(ip, port);

			IdcContext idcContext = new IdcContext(username, password);
			String ext = FilenameUtils.getExtension(file.getName());
			String type = getMimeType(ext);
			DataBinder requestDataBinder = idcClient.createBinder();

			requestDataBinder.putLocal("IdcService", "CHECKIN_UNIVERSAL");
			requestDataBinder.putLocal("dDocType", DOCUMENT_TYPE);
			requestDataBinder.putLocal("dDocTitle", file.getName());
			requestDataBinder.putLocal("dDocAuthor", username);
			requestDataBinder.putLocal("dSecurityGroup", SECURITY_GROUP);
			requestDataBinder.putLocal("dFormat", type);
			if(properties != null) {
				for (Entry<String, String> pair : properties.entrySet()) {
					requestDataBinder.putLocal(pair.getKey(), pair.getValue());
				}
			}
			requestDataBinder.addFile("primaryFile", new TransferFile(is, file.getName(), file.length(), type));

			serviceResponse = idcClient.sendRequest(idcContext, requestDataBinder);
			DataBinder responseDataBinder = serviceResponse.getResponseAsBinder();
			contentId = responseDataBinder.getLocal("dDocName");

		} catch (Exception e) {
			throw e;
		} finally {
			if (serviceResponse != null) {
				serviceResponse.close();
			}
		}
		return contentId;
	}

	@Override
	public String checkin(Map<String, String> properties, MultipartFile file) throws Exception {
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String fullPath = URLDecoder.decode(path, "UTF-8");
		File orginFile = new File(fullPath + File.separatorChar + file.getOriginalFilename());
		FileUtils.copyInputStreamToFile(file.getInputStream(), orginFile);
		return checkin(properties, orginFile);
	}

	@Override
	public void getProperties(String contentId) throws Exception {

		ServiceResponse serviceResponse = null;

		try {
			StringBuilder query = new StringBuilder();
			IdcClient<?, ?, ?> idcClient = getIdcClient(ip, port);
			IdcContext idcContext = new IdcContext(username, password);
			DataBinder requestDataBinder = idcClient.createBinder();
			requestDataBinder.putLocal("SortField", "dInDate");
			requestDataBinder.putLocal("SortOrder", "Desc");
			requestDataBinder.putLocal("IdcService", "GET_SEARCH_RESULTS");
			query.append("dDocName <matches> `");
			query.append(contentId);
			query.append("`");
			requestDataBinder.putLocal("QueryText", query.toString());
			requestDataBinder.putLocal("ResultCount", "20");
			requestDataBinder.putLocal("SortField", "dInDate");
			requestDataBinder.putLocal("SortOrder", "Desc");
			serviceResponse = idcClient.sendRequest(idcContext, requestDataBinder);
			DataBinder responseDataBinder = serviceResponse.getResponseAsBinder();
			DataResultSet dataResultSet = responseDataBinder.getResultSet("SearchResults");
			int count = 1;
			for (DataObject dataObject : dataResultSet.getRows()) {
				System.out.println("---------------------------count=" + count);
				System.out.println("Id: " + dataObject.get("dID"));
				System.out.println("ContentId: " + dataObject.get("dDocName"));
				System.out.println("Title of content: " + dataObject.get("dDocTitle"));
				System.out.println("Author: " + dataObject.get("dDocAuthor"));
				System.out.println("Release date: " + dataObject.get("dInDate"));
				// System.out.println("Folder id: " +
				// Long.toString(getFolderIdFromPath("/Contribution Folders/")));
				System.out.println("Mapped folder URL: " + dataObject.get("mappedFolderURL"));
				System.out.println("Total rows: " + dataObject.get("TotalRows"));
				count++;
			}

		} finally {
			if (serviceResponse != null) {
				serviceResponse.close();
			}
		}
	}

	@Override
	public InputStream getContent(String contentId) throws Exception {

		InputStream inputStream = null;
		ServiceResponse serviceResponse = null;

		try {

			IdcClient<?, ?, ?> idcClient = getIdcClient(ip, port);
			IdcContext idcContext = new IdcContext(username, password);
			DataBinder requestDataBinder = idcClient.createBinder();
			requestDataBinder.putLocal("IdcService", "GET_FILE");
			requestDataBinder.putLocal("RevisionSelectionMethod", "Latest");
			requestDataBinder.putLocal("dDocName", contentId);
			serviceResponse = idcClient.sendRequest(idcContext, requestDataBinder);
			inputStream = serviceResponse.getResponseStream();

		} finally {
			if (serviceResponse != null) {
				serviceResponse.close();
			}

		}

		return inputStream;
	}

	@Override
	public void delete(String contentId) throws Exception {

		ServiceResponse serviceResponse = null;

		try {
			IdcClient<?, ?, ?> idcClient = getIdcClient(ip, port);
			IdcContext idcContext = new IdcContext(username, password);
			DataBinder requestDataBinder = idcClient.createBinder();
			requestDataBinder.putLocal("IdcService", "DELETE_DOC");

			requestDataBinder.putLocal("dDocName", contentId);
			serviceResponse = idcClient.sendRequest(idcContext, requestDataBinder);

		} finally {

			if (serviceResponse != null) {

				serviceResponse.close();

			}

		}

	}

	private String getMimeType(String extention) {
		switch (extention.toLowerCase()) {
		case "pdf":
			return "application/pdf";
		case "jpg":
			return "image/jpeg";
		case "png":
			return "image/png";
		default:
			return null;
		}
	}

	@Override
	public Map<String, String> getAttachmentProperties(String folder, String attachmentType, String requestSerial) throws Exception {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("xmodule_code", "");
		properties.put("xservice_code", "");
		properties.put("xdoc_upload_source", "");
		properties.put("xattachment_type", attachmentType);
		properties.put("xrequest_serial", requestSerial);
		properties.put("xCollectionID", getFolderIdFromPath(username, "/" + folder + "/"));
		return properties;
	}

	// Returns Folder ID
	public String getFolderIdFromPath(String username, String path) throws Exception {
		String folderId = null;

		try {
			IdcClient<?, ?, ?> client = getIdcClient(ip, port);
			DataBinder dataBinder = client.createBinder();
			dataBinder.putLocal("IdcService", "COLLECTION_INFO");
			dataBinder.putLocal("hasCollectionPath", "true");
			dataBinder.putLocal("dCollectionPath", path);

			ServiceResponse response = client.sendRequest(new IdcContext(username), dataBinder);
			DataBinder serverBinder = response.getResponseAsBinder();
			DataResultSet resultSet = serverBinder.getResultSet("PATH");
			DataObject dataObject = resultSet.getRows().get(resultSet.getRows().size() - 1);
			folderId = dataObject.get("dCollectionID");

		} catch (Exception ex) {
			throw ex;
		}

		return folderId;
	}

	public String createFolder(String folderName, Boolean Has_Parent, String ParentCollectionID) throws Exception {
		ServiceResponse severiceResponse = null;
		String folderId = null;
		try {
			System.out.println("Creating Folder: " + folderName);

			IdcClient<?, ?, ?> client = getIdcClient(ip, port);
			DataBinder dataBinderReq = client.createBinder();
			dataBinderReq.putLocal("IdcService", "COLLECTION_ADD");
			dataBinderReq.putLocal("dCollectionName", folderName);
			dataBinderReq.putLocal("hasParentCollectionID", Has_Parent.toString());
			dataBinderReq.putLocal("dParentCollectionID", ParentCollectionID);
			dataBinderReq.putLocal("dCollectionOwner", username);
			dataBinderReq.putLocal("dSecurityGroup", SECURITY_GROUP);

			severiceResponse = client.sendRequest(new IdcContext(username), dataBinderReq);
			DataBinder dataBinderResp = severiceResponse.getResponseAsBinder();
			DataResultSet resultSet = dataBinderResp.getResultSet("PATH");
			DataObject dataObject = resultSet.getRows().get(resultSet.getRows().size() - 1);
			folderId = dataObject.get("dCollectionID");

		} catch (Exception ex) {
			throw ex;
		} finally {
			if (severiceResponse != null) {
				severiceResponse.close();
			}
		}
		return folderId;
	}

}
