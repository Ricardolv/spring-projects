package com.richard.brewer.repository.help.city;

import com.richard.brewer.model.City;
import com.richard.brewer.repository.filter.CityFilter;
import com.richard.brewer.repository.pagination.PaginationUtil;
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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.sql.JoinType;

@SuppressWarnings({"deprecation", "unchecked"})
public class CitysImpl implements CitysQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginationUtil paginationUtil;

	@Transactional(readOnly = true)
	@Override
	public Page<City> filter(CityFilter filter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(City.class);
		
		paginationUtil.prepare(criteria, pageable);
		addFilter(filter, criteria);
		
		criteria.createAlias("state", "e");
				
		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}
	
	@Transactional(readOnly = true)
	@Override
	public City findOfState(Long code) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(City.class);
		criteria.createAlias("state", "s", JoinType.LEFT_OUTER_JOIN );
		criteria.add(Restrictions.eq("code", code));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (City) criteria.uniqueResult();
	}
	
	private Long total(CityFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(City.class);
		addFilter(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void addFilter(CityFilter filter, Criteria criteria) {
		
		if (filter != null) {
			
			if (filter.getState() != null) {
				criteria.add(Restrictions.eq("state", filter.getState()));
			}
			
			if (!StringUtils.isEmpty(filter.getName())) {
				criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
			}
		}
	}

	

}
