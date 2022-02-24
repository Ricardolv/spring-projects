package com.richard.brewer.repository.pagination;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginationUtil {
	
	
	public void prepare(Criteria criteria, Pageable pageable) {

		criteria.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		criteria.setMaxResults(pageable.getPageSize());
		
		Sort sort = pageable.getSort();
		if (null != sort && sort.isSorted()) {
			Sort.Order order = sort.iterator().next();
			String property = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(property) : Order.desc(property));
		}
	}

}
