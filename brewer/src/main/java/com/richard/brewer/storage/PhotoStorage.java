package com.richard.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoStorage {

	public String saveTemporarily(MultipartFile[] files);

	public void save(String photo);

	public byte[] recoverTemporary(String name);

	public byte[] recover(String name);
	
	public byte[] recoverThumbnail(String string);

	public void delete(String photo);
	
}
