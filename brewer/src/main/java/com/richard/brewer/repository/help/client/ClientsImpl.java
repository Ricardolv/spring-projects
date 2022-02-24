package com.richard.brewer.repository.help.client;

import com.richard.brewer.model.Client;
import com.richard.brewer.repository.filter.ClientFilter;
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

public class ClientsImpl implements ClientsQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginationUtil paginationUtil;

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	@Transactional(readOnly = true)
	public Page<Client>filter(ClientFilter clientFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Client.class);
		
		paginationUtil.prepare(criteria, pageable);
		addFilter(clientFilter, criteria);
		criteria.createAlias("anddress.city", "c", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("c.state", "e", JoinType.LEFT_OUTER_JOIN);
		
		return new PageImpl<>(criteria.list(), pageable, total(clientFilter));
	}
	

	@SuppressWarnings("deprecation")
	private Long total(ClientFilter clientFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Client.class);
		addFilter(clientFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void addFilter(ClientFilter clientFilter, Criteria criteria) {
		if (clientFilter != null) {
			if (!StringUtils.isEmpty(clientFilter.getName())) {
				criteria.add(Restrictions.ilike("name", clientFilter.getName(), MatchMode.ANYWHERE));
			}

			if (!StringUtils.isEmpty(clientFilter.getCpfCnpj())) {
				criteria.add(Restrictions.eq("cpfCnpj", clientFilter.getCpfCnpjWithoutFormatting()));
			}
		}
	}

}
