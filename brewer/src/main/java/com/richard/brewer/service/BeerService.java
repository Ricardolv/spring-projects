package com.richard.brewer.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richard.brewer.dto.BeerDTO;
import com.richard.brewer.model.Beer;
import com.richard.brewer.repository.Beers;
import com.richard.brewer.repository.filter.BeerFilter;
import com.richard.brewer.service.event.beer.BeerSaveEvent;
import com.richard.brewer.service.exception.ImpossibleDeleteEntityException;
import com.richard.brewer.storage.PhotoStorage;

@Service
public class BeerService {
	
	@Autowired
	private Beers beers;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PhotoStorage photoStorage;
	
	@Transactional
	public void save(Beer beer) {
		beers.save(beer);
		
		publisher.publishEvent(new BeerSaveEvent(beer));
	}
	
	@Transactional
	public void delete(Beer beer) {
		try {
			String photo = beer.getPhoto();
			beers.delete(beer);
			beers.flush();
			photoStorage.delete(photo);
		} catch (PersistenceException e) {
			throw new ImpossibleDeleteEntityException("Impossível apagar cerveja. Já foi usada em alguma venda.");
		}
		
	}

	public List<Beer> findAll() {
		return beers.findAll();
	}
	
	public Page<Beer> filter(BeerFilter beerFilter, Pageable pageable) {
		return beers.filter(beerFilter, pageable);
	}

	public List<BeerDTO> bySkuOrName(String skuOrName) {
		return beers.bySkuOrName(skuOrName);
	}

	
}
