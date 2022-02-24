package com.richard.brewer.repository.help.user;

import com.richard.brewer.model.Group;
import com.richard.brewer.model.User;
import com.richard.brewer.model.UserGroup;
import com.richard.brewer.repository.filter.UserFilter;
import com.richard.brewer.repository.pagination.PaginationUtil;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersImpl implements UsersQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginationUtil paginationUtil;

	@Override
	public Optional<User> findByEmailAndActive(String email) {
		return manager.createQuery(
				"from User where lower(email) = lower(:email) and active = true ", User.class)
				.setParameter("email", email).getResultList().stream().findFirst();
	}

	@Override
	public List<String> permissions(User user) {
		return manager.createQuery(
				"select distinct p.name from User u inner join u.groups g inner join g.permissions p where u = :user", String.class)
				.setParameter("user", user)
				.getResultList();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Transactional(readOnly = true)
	@Override
	public Page<User> filter(UserFilter filter, Pageable pageable) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(User.class);
		
		paginationUtil.prepare(criteria, pageable);
		addFilter(filter, criteria);
		
		List<User> listFilter = criteria.list();
		listFilter.forEach(u -> Hibernate.initialize(u.getGroups()));
		
		return new PageImpl<>(listFilter, pageable, total(filter));
		
	}
	
	@SuppressWarnings("deprecation")
	@Transactional(readOnly = true)
	@Override
	public User findOfGroups(Long code) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(User.class);
		criteria.createAlias("groups", "g", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("code", code));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		return (User) criteria.uniqueResult();
		
	}
	
	@SuppressWarnings("deprecation")
	private Long total(UserFilter userFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(User.class);
		
		addFilter(userFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void addFilter(UserFilter filter, Criteria criteria) {

		if (null!= filter) {
			
			if (!StringUtils.isEmpty(filter.getName())) {
				criteria.add(Restrictions.ilike("name", filter.getName(), MatchMode.ANYWHERE));
			}
			
			if (!StringUtils.isEmpty(filter.getEmail())) {
				criteria.add(Restrictions.ilike("email", filter.getEmail(), MatchMode.START));
			}
			
			if (null != filter.getGroups() && !filter.getGroups().isEmpty()) {
				
				List<Criterion> subqueries = new ArrayList<>();
				for(Long codeGroup : filter.getGroups().stream().mapToLong(Group::getCode).toArray()) {
					DetachedCriteria dc = DetachedCriteria.forClass(UserGroup.class);
					dc.add(Restrictions.eq("id.group.code", codeGroup));
					dc.setProjection(Projections.property("id.user"));
					
					subqueries.add(Subqueries.propertyIn("code", dc));
				}
				
				Criterion[] criterions = new Criterion[subqueries.size()];
				criteria.add(Restrictions.and(subqueries.toArray(criterions)));
			}
			
		}
	}

}
