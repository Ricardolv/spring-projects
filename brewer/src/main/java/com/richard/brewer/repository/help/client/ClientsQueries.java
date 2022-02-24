package com.richard.brewer.repository.help.client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.richard.brewer.model.Client;
import com.richard.brewer.repository.filter.ClientFilter;

public interface ClientsQueries {
	
	public Page<Client> filter(ClientFilter clientFilter, Pageable pageable);

}
