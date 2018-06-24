package com.informatique.gov.judicialwarrant.support.integration.contentmanger;

import java.io.File;
import java.io.Serializable;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ContentManager extends Serializable {

	public String checkin(Map<String, String> properties, File file) throws Exception;

	public String checkin(Map<String, String> properties, MultipartFile file) throws Exception;

	public void getProperties(String contentId) throws Exception;

	public byte[] getContent(String contentId) throws Exception;

	public void delete(String contentId) throws Exception;

	public Map<String, String> getAttachmentProperties(String folder, String attachmentType, String requestSerial) throws Exception;

	public String createFolder(String folderName, Boolean Has_Parent, String ParentCollectionID) throws Exception;

	public String getFolderIdFromPath(String path) throws Exception;
	
}
