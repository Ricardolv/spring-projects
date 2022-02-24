package com.richard.brewer.storage.local;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.richard.brewer.storage.PhotoStorage;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@Profile("!prod")
@Component
public class PhotoStorageLocal implements PhotoStorage {
	
	private static final Logger logger = LoggerFactory.getLogger(PhotoStorageLocal.class);
	
	private static final String THUMBNAIL_PREFIX = "thumbnail.";
	
	@Value("${brewer.foto-storage-local.local}")
	private Path local;
	
	private Path localTemporary;
	

	public PhotoStorageLocal() {
		this.local = FileSystems.getDefault().getPath(System.getenv("HOME"), ".brewerphotos");
		createPast();
	}
	
	public PhotoStorageLocal(Path path) {
		this.local = path;
		createPast();
	}
	
	@Override
	public String saveTemporarily(MultipartFile[] files) {
		String newName = null;
		
		if (files != null && files.length > 0) {
			MultipartFile multipartFile = files[0];
			newName = fileRename(multipartFile.getOriginalFilename());
			try {
				multipartFile.transferTo(new File(this.localTemporary.toAbsolutePath().toString() + FileSystems.getDefault().getSeparator() + newName));
			} catch (IOException e) {
				throw new RuntimeException("Erro salvando a foto na pasta temporária", e);
			}
		}
		
		return newName;
	}
	
	@Override
	public byte[] recoverTemporary(String name) {
		try {
			return Files.readAllBytes(this.localTemporary.resolve(name));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto temporaria", e);
		}
	}
	
	@Override
	public byte[] recover(String name) {
		try {
			return Files.readAllBytes(this.local.resolve(name));
		} catch (IOException e) {
			throw new RuntimeException("Erro lendo a foto", e);
		}
	}
	
	@Override
	public void save(String photo) {
		
		try {
			Files.move(this.localTemporary.resolve(photo), this.local.resolve(photo));
		} catch (IOException e) {
			throw new RuntimeException("Erro movendo a foto para destino final", e);
		}
		
		try {
			Thumbnails.of(this.local.resolve(photo).toString()).size(40, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Erro gerando Thumbnail", e);
		}
		
	}
	
	@Override
	public byte[] recoverThumbnail(String beerPhoto) {
		return recover(THUMBNAIL_PREFIX + beerPhoto);
	}
	
	@Override
	public void delete(String photo) {
		try {
			Files.deleteIfExists(this.local.resolve(photo));
			Files.deleteIfExists(this.local.resolve(THUMBNAIL_PREFIX + photo));
		} catch (IOException e) {
			logger.warn(String.format("Erro apagando foto '%s'. Mensagem: %s", photo, e.getMessage()));
		}
		
	}

	@PostConstruct
	private void createPast() {
		try {
			Files.createDirectories(this.local);
			this.localTemporary = FileSystems.getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporary);
			
			if (logger.isDebugEnabled()) {
				logger.debug("Pasta criadas para salvar fotos.");
				logger.debug("Pasta default: " + this.local.toAbsolutePath());
				logger.debug("Pasta temporaria: " + this.localTemporary.toAbsolutePath());
			}
			
		} catch (IOException e) {
			throw new RuntimeException("Error create past for save image", e);
		}
	}

	private String fileRename(String originalFilename) {
		
		String newName = UUID.randomUUID().toString() + "_" + originalFilename;
		
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Nome original: %s, novo nome: %s", originalFilename, newName));
		}
		return newName;
	}

	

}
