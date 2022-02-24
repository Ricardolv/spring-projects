package com.richard.brewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richard.brewer.model.State;
import com.richard.brewer.repository.States;

@Service
public class StateService {
	
	@Autowired
	private States states;
	
	public List<State> findAll() {
		return states.findAll();
	}

}
