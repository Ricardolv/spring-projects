package com.richard.brewer.repository.help.beer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.richard.brewer.dto.BeerDTO;
import com.richard.brewer.dto.StockValueItems;
import com.richard.brewer.model.Beer;
import com.richard.brewer.repository.filter.BeerFilter;
import com.richard.brewer.repository.pagination.PaginationUtil;

public class BeersImpl implements BeersQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginationUtil paginationUtil;

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional(readOnly = true)
	@Override
	public Page<Beer> filter(BeerFilter beerFilter, Pageable pageable) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Beer.class);
		
		paginationUtil.prepare(criteria, pageable);
		addFilter(beerFilter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(beerFilter));
	}
	
	@Override
	public StockValueItems valueItemsStock() {
		String query = "select new com.richard.brewer.dto.StockValueItems(sum(value * quantityStock), sum(quantityStock)) from Beer";
		return manager.createQuery(query, StockValueItems.class).getSingleResult();
		
	}

	@SuppressWarnings("deprecation")
	private Long total(BeerFilter beerFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Beer.class);
		addFilter(beerFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private boolean isStylePresent(BeerFilter filter) {
		return filter.getStyle() != null && filter.getStyle().getCode() != null;
	}
	
	private void addFilter(BeerFilter beerFilter, Criteria criteria) {
		if (null != beerFilter) {
			
			if (!StringUtils.isEmpty(beerFilter.getSku())) {
				criteria.add(Restrictions.eq("sku", beerFilter.getSku()));
			}
			
			if (!StringUtils.isEmpty(beerFilter.getName())) {
				criteria.add(Restrictions.ilike("name", beerFilter.getName(), MatchMode.ANYWHERE));
			}

			if (isStylePresent(beerFilter)) {
				criteria.add(Restrictions.eq("style", beerFilter.getStyle()));
			}

			if (beerFilter.getFlavor() != null) {
				criteria.add(Restrictions.eq("flavor", beerFilter.getFlavor()));
			}

			if (beerFilter.getOrigin() != null) {
				criteria.add(Restrictions.eq("origin", beerFilter.getOrigin()));
			}

			if (beerFilter.getPriceOf() != null) {
				criteria.add(Restrictions.ge("value", beerFilter.getPriceOf()));
			}

			if (beerFilter.getPriceUntil() != null) {
				criteria.add(Restrictions.le("value", beerFilter.getPriceUntil()));
			}
			
		}
	}

	@Override
	public List<BeerDTO> bySkuOrName(String skuOrName) {

		String jpql = "select new com.richard.brewer.dto.BeerDTO(code, sku, name, origin, value, photo) "
				+ "from Beer where lower(sku) like lower(:skuOrName) or lower(name) like lower(:skuOrName) and quantityStock > 0 ";
		List<BeerDTO> beersFiltered= manager.createQuery(jpql, BeerDTO.class)
					.setParameter("skuOrName", skuOrName + "%")
					.getResultList();
		return beersFiltered;
	}

}
