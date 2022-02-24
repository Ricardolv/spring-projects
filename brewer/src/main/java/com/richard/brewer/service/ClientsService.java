package com.richard.brewer.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richard.brewer.model.Client;
import com.richard.brewer.repository.Clients;
import com.richard.brewer.repository.filter.ClientFilter;
import com.richard.brewer.service.exception.ClientCpfCnpjExistsException;
import com.richard.brewer.service.exception.ImpossibleDeleteEntityException;

@Service
public class ClientsService {
	
	@Autowired
	private Clients clients;
	
	@Transactional
	public void save(Client client) {
		
		Optional<Client> clientExist = clients.findByCpfCnpj(client.getCpfCnpjWithoutFormatting());
		
		if (clientExist.isPresent() && !client.equals(clientExist.get())) {
			throw new ClientCpfCnpjExistsException("CPF/CNPJ já cadastrado");
		}
		
		clients.save(client);
	}
	
	@Transactional
	public void delete(Client client) {
		try {
			clients.delete(client);
			clients.flush();
		} catch (PersistenceException e) {
			throw new ImpossibleDeleteEntityException("Impossível apagar cliente.");
		}
		
	}

	public Page<Client> filter(ClientFilter clientFilter, Pageable pageable) {
		return clients.filter(clientFilter, pageable);
	}

	public List<Client> findByNameStartingWithIgnoreCase(String name) {
		return clients.findByNameStartingWithIgnoreCase(name);
	}
	
	public Client findOne(Long code) {
		return clients.getOne(code);
	}


}
