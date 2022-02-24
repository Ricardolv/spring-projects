package com.richard.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.richard.brewer.dto.PhotosDTO;
import com.richard.brewer.storage.PhotoStorage;
import com.richard.brewer.storage.PhotoStorageRunnable;

@RestController
@RequestMapping("/photos")
public class PhotosControler {
	
	@Autowired
	private PhotoStorage photoStorage;
	
	@PostMapping
	public DeferredResult<PhotosDTO> upload(@RequestParam("files[]") MultipartFile[] files) {
		DeferredResult<PhotosDTO> result = new DeferredResult<>();
		
		Thread thread = new Thread(new PhotoStorageRunnable(files, result, photoStorage));
		thread.start();
		
		return result;
	}
	
	@GetMapping("/temp/{name:.*}")
	public byte[] recoverTemporary(@PathVariable String name) {
		return photoStorage.recoverTemporary(name);
	}
	
	@GetMapping("/{name:.*}")
	public byte[] recover(@PathVariable String name) {
		return photoStorage.recover(name);
	}

}
