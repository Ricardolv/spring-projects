package com.richard.brewer.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richard.brewer.model.Style;
import com.richard.brewer.repository.Styles;
import com.richard.brewer.repository.filter.StyleFilter;
import com.richard.brewer.service.exception.ImpossibleDeleteEntityException;
import com.richard.brewer.service.exception.NameExistsException;

@Service
public class StyleService {
	
	@Autowired
	private Styles styles;
	
	public List<Style> findAll() {
		return styles.findAll();
	}

	@Transactional
	public Style save(Style style) {
		
		Optional<Style> styleExist = styles.findByNameIgnoreCase(style.getName());
		
		if (styleExist.isPresent()) {
			throw new NameExistsException("Nome do estilo já cadastrado");
		}	
		
		return styles.saveAndFlush(style);
	}

	@Transactional
	public void delete(Style style) {
		try {
			styles.delete(style);
			styles.flush();
		} catch (PersistenceException e) {
			throw new ImpossibleDeleteEntityException("Impossível apagar estilo. Já foi usada em alguma cerveja.");
		}
		
	}
	
	public Page<Style> filter(StyleFilter styleFilter, Pageable pageable) {
		return styles.filter(styleFilter, pageable);
	}

	public Style findOne(Long code) {
		return styles.getOne(code);
	}


}
