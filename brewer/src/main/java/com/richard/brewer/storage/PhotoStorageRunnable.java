package com.richard.brewer.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.richard.brewer.dto.PhotosDTO;

public class PhotoStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<PhotosDTO> result;
	private PhotoStorage photoStorage;

	public PhotoStorageRunnable(MultipartFile[] files, DeferredResult<PhotosDTO> result, PhotoStorage photoStorage) {
		this.files = files;
		this.result = result;
		this.photoStorage = photoStorage;
	}

	@Override
	public void run() {
		String name = this.photoStorage.saveTemporarily(files);
		String contentType = files[0].getContentType();
		result.setResult(new PhotosDTO(name, contentType));
	}

}
