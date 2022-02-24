package com.richard.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richard.brewer.model.Client;
import com.richard.brewer.repository.help.client.ClientsQueries;

@Repository
public interface Clients extends JpaRepository<Client, Long>, ClientsQueries {

	public Optional<Client> findByCpfCnpj(String cpfOrCnpj);

	public List<Client> findByNameStartingWithIgnoreCase(String name);

}
