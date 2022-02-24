package com.richard.brewer.repository.help.sale;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.richard.brewer.dto.SaleByMonth;
import com.richard.brewer.dto.SaleOrigin;
import com.richard.brewer.model.Sale;
import com.richard.brewer.repository.filter.SaleFilter;

public interface SalesQueries {
	
	public Page<Sale> filter(SaleFilter filtro, Pageable pageable);
	public Sale findOfItmes(Long code);
	
	public BigDecimal totalValueYear();
	public BigDecimal totalValueMonth();
	public BigDecimal tickedValue();
	
	public List<SaleByMonth> totalByMonth();
	public List<SaleOrigin> totalByOrigin();
	
}
