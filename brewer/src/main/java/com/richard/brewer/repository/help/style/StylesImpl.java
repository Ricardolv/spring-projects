package com.richard.brewer.repository.help.style;

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

import com.richard.brewer.model.Style;
import com.richard.brewer.repository.filter.StyleFilter;
import com.richard.brewer.repository.pagination.PaginationUtil;

public class StylesImpl implements StylesQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginationUtil paginationUtil;

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional(readOnly = true)
	@Override
	public Page<Style> filter(StyleFilter styleFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Style.class);
		
		paginationUtil.prepare(criteria, pageable);
		addFilter(styleFilter, criteria);

		return new PageImpl<>(criteria.list(), pageable, total(styleFilter));
		
	}
	
	@SuppressWarnings("deprecation")
	private Long total(StyleFilter styleFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Style.class);
		addFilter(styleFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
		
	}
	
	private void addFilter(StyleFilter styleFilter, Criteria criteria) {
		if (!StringUtils.isEmpty(styleFilter.getName())) {
			criteria.add(Restrictions.ilike("name", styleFilter.getName(), MatchMode.ANYWHERE));
		}
	}

	
}
