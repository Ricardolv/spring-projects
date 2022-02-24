package com.richard.brewer.repository.help.beer;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.richard.brewer.dto.BeerDTO;
import com.richard.brewer.dto.StockValueItems;
import com.richard.brewer.model.Beer;
import com.richard.brewer.repository.filter.BeerFilter;

public interface BeersQueries {
	
	public Page<Beer> filter(BeerFilter beerFilter, Pageable pageable);
	
	public List<BeerDTO> bySkuOrName(String skuOrName);
	
	public StockValueItems valueItemsStock();

}
