package com.richard.brewer.repository.help.sale;

import com.richard.brewer.dto.SaleByMonth;
import com.richard.brewer.dto.SaleOrigin;
import com.richard.brewer.model.PersonType;
import com.richard.brewer.model.Sale;
import com.richard.brewer.model.SaleStatus;
import com.richard.brewer.repository.filter.SaleFilter;
import com.richard.brewer.repository.pagination.PaginationUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"deprecation", "unchecked"})
public class SalesImpl implements SalesQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginationUtil paginationUtil;

	@Transactional(readOnly = true)
	@Override
	public Page<Sale> filter(SaleFilter filter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Sale.class);
		paginationUtil.prepare(criteria, pageable);
		addFilter(filter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filter));
	}

	@Transactional(readOnly = true)
	@Override
	public Sale findOfItmes(Long code) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Sale.class);
		criteria.createAlias("items", "i", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("code", code));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Sale) criteria.uniqueResult();
	}
	
	@Override
	public BigDecimal totalValueMonth() {
		Optional<BigDecimal> optional = Optional.ofNullable(manager.createQuery("select sum(totalValue) from Sale where month(creationDate) = :month and status = :status", BigDecimal.class)
				.setParameter("month", MonthDay.now().getMonthValue())
				.setParameter("status", SaleStatus.ISSUED)
				.getSingleResult());
			
			return optional.orElse(BigDecimal.ZERO);
	}
	
	@Override
	public BigDecimal totalValueYear() {
	Optional<BigDecimal> optional = Optional.ofNullable(manager.createQuery("select sum(totalValue) from Sale where year(creationDate) = :year and status = :status", BigDecimal.class)
			.setParameter("year", Year.now().getValue())
			.setParameter("status", SaleStatus.ISSUED)
			.getSingleResult());
		
		return optional.orElse(BigDecimal.ZERO);
	}
	
	@Override
	public BigDecimal tickedValue() {
		Optional<BigDecimal> optional = Optional.ofNullable(manager.createQuery("select sum(totalValue) /count(*) from Sale where year(creationDate) = :year and status = :status", BigDecimal.class)
				.setParameter("year", Year.now().getValue())
				.setParameter("status", SaleStatus.ISSUED)
				.getSingleResult());
			
			return optional.orElse(BigDecimal.ZERO);
	}
	
	@Override
	public List<SaleByMonth> totalByMonth() {
		List<SaleByMonth> saleByMonths = manager.createNamedQuery("Sales.totalByMonth").getResultList();
		
		if (!saleByMonths.isEmpty()) {
			LocalDate now = LocalDate.now();
			
			for (int i = 0; i < 6; i++) {
				String idealMonth = String.format("%d/%02d", now.getYear(), now.getMonthValue());
				
				boolean haveMonth = saleByMonths.stream().filter(s -> s.getMonth().equals(idealMonth)).findAny().isPresent();
				if (!haveMonth) {
					saleByMonths.add(i - 1, new SaleByMonth(idealMonth, 0));
				}
				
				now = now.minusMonths(1);
			}
			Collections.sort(saleByMonths, (SaleByMonth s1, SaleByMonth s2) -> s2.getMonth().compareTo(s1.getMonth()));
		}
		
		return saleByMonths;
	}
	
	@Override
	public List<SaleOrigin> totalByOrigin() {
		List<SaleOrigin> salesByNationality = manager.createNamedQuery("Sales.byOrigin", SaleOrigin.class).getResultList();

		if (!salesByNationality.isEmpty()) {
			LocalDate now = LocalDate.now();
			
			for (int i = 1; i <= 6; i++) {
				String idealMonth = String.format("%d/%02d", now.getYear(), now.getMonth().getValue());

				boolean haveMonth = salesByNationality.stream().filter(v -> v.getMonth().equals(idealMonth)).findAny().isPresent();
				if (!haveMonth) {
					salesByNationality.add(i - 1, new SaleOrigin(idealMonth, 0, 0));
				}

				now = now.minusMonths(1);
			}
			Collections.sort(salesByNationality, (SaleOrigin s1, SaleOrigin s2) -> s2.getMonth().compareTo(s1.getMonth()));
		}
		
		return salesByNationality;
	}
	
	private Long total(SaleFilter filter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Sale.class);
		addFilter(filter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void addFilter(SaleFilter filter, Criteria criteria) {
		criteria.createAlias("client", "c");

		if (filter != null) {
			if (!StringUtils.isEmpty(filter.getCode())) {
				criteria.add(Restrictions.eq("code", filter.getCode()));
			}

			if (filter.getStatus() != null) {
				criteria.add(Restrictions.eq("status", filter.getStatus()));
			}

			if (filter.getSince() != null) {
				LocalDateTime since = LocalDateTime.of(filter.getSince(), LocalTime.of(0, 0));
				criteria.add(Restrictions.ge("creationDate", since));
			}

			if (filter.getUpUntil() != null) {
				LocalDateTime upUntil = LocalDateTime.of(filter.getUpUntil(), LocalTime.of(23, 59));
				criteria.add(Restrictions.le("creationDate", upUntil));
			}

			if (filter.getMinimumValue() != null) {
				criteria.add(Restrictions.ge("totalValue", filter.getMinimumValue()));
			}

			if (filter.getMaximumValue() != null) {
				criteria.add(Restrictions.le("totalValue", filter.getMaximumValue()));
			}

			if (!StringUtils.isEmpty(filter.getClientName())) {
				criteria.add(Restrictions.ilike("c.name", filter.getClientName(), MatchMode.ANYWHERE));
			}

			if (!StringUtils.isEmpty(filter.getClientCpfCnpj())) {
				criteria.add(Restrictions.eq("c.cpfCnpj", PersonType.removeFormatting(filter.getClientCpfCnpj())));
			}
		}
	}

}
