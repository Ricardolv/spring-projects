package com.richard.brewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.richard.brewer.model.Group;
import com.richard.brewer.repository.Groups;

@Service
public class GroupsService {
	
	@Autowired
	private Groups groups;

	public List<Group> findAll() {
		return groups.findAll();
	}
	
	@Transactional
	public void save(Group group) {
		
		groups.save(group);
	}
	

}
