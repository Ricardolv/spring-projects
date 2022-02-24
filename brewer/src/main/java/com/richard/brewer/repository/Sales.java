package com.richard.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.Sale;
import com.richard.brewer.repository.help.sale.SalesQueries;

@Repository
public interface Sales extends JpaRepository<Sale, Long>, SalesQueries {

}
