package com.informatique.gov.judicialwarrant.support.integration;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.io.IOUtils;

import oracle.stellent.ridc.IdcClient;
import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.IdcClientManager;
import oracle.stellent.ridc.IdcContext;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.model.DataObject;
import oracle.stellent.ridc.model.DataResultSet;
import oracle.stellent.ridc.model.TransferFile;
import oracle.stellent.ridc.protocol.ServiceResponse;

public class UcmClient {

	private final String ip, port, username;
	private final char[] password;
	private final static String DOCUMENT_TYPE = "Application";
	private final static String SECURITY_GROUP = "Public";

	public UcmClient(String ip, String port, String username, String password) {
    
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password.toCharArray();
	}

	private IdcClient<?, ?, ?> getIdcClient(String ip, String port) throws IdcClientException {

		IdcClientManager idcClientManager = new IdcClientManager();
		IdcClient<?, ?, ?> idcClient = idcClientManager.createClient("idc://" + ip + ":" + port);
		return idcClient;

	}

	public String checkin(String moduleCode, String serviceCode, String uploadSource, String attachmentType,
			String requestSerial, File file)
			throws IOException, IdcClientException {

		
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
			requestDataBinder.putLocal("xmodule_code", moduleCode);
			requestDataBinder.putLocal("xservice_code", serviceCode);
			requestDataBinder.putLocal("xdoc_upload_source", uploadSource);
			requestDataBinder.putLocal("xattachment_type", attachmentType);
			requestDataBinder.putLocal("xrequest_serial", requestSerial);
			requestDataBinder.addFile("primaryFile", new TransferFile(is, file.getName(), file.length(), type));
			
			serviceResponse = idcClient.sendRequest(idcContext, requestDataBinder);
			DataBinder responseDataBinder = serviceResponse.getResponseAsBinder();
			contentId = responseDataBinder.getLocal("dDocName");

		} finally {
			if (serviceResponse != null) {
				serviceResponse.close();
			}
		}
		return contentId;
	}
	
    public void getProperties(String contentId) throws IdcClientException {


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
                //  System.out.println("Folder id: " + Long.toString(getFolderIdFromPath("/Contribution Folders/")));
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
    
    
    public InputStream getContent(String contentId) throws IdcClientException, IOException {

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
    
//    public GetFileResponse getContent(String contentId) throws IdcClientException, IOException {
//
//        InputStream inputStream = null;
//        ServiceResponse serviceResponse = null;
//        GetFileResponse getFileResponse = null;
//        try {
//                
//                IdcClient<?, ?, ?> idcClient = getIdcClient(ip, port);
//            IdcContext idcContext = new IdcContext(username, password);
//            DataBinder requestDataBinder = idcClient.createBinder();
//            requestDataBinder.putLocal("IdcService", "GET_FILE");
//            requestDataBinder.putLocal("RevisionSelectionMethod", "Latest");
//            requestDataBinder.putLocal("dDocName", contentId);
//            serviceResponse = idcClient.sendRequest(idcContext, requestDataBinder);
//            String contentLength = serviceResponse.getHeader("content-length");
//            String contentType = serviceResponse.getHeader("content-type");
//            inputStream = serviceResponse.getResponseStream();
//            byte[] content = IOUtils.toByteArray(inputStream);
//            getFileResponse = new GetFileResponse(content, contentType, Long.parseLong(contentLength));
//        } finally {
//                if (serviceResponse != null) {
//                                serviceResponse.close();
//                        }
//                
//                if (inputStream != null) {
//                        inputStream.close();
//                        }
//        }
//
//        return getFileResponse;
//    }
//    
    public void delete(String contentId) throws IdcClientException {

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
		switch(extention.toLowerCase()) {
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
	
}
