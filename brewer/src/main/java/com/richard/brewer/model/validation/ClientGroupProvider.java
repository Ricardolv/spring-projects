package com.richard.brewer.model.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.richard.brewer.model.Client;

public class ClientGroupProvider implements DefaultGroupSequenceProvider<Client> {

	@Override
	public List<Class<?>> getValidationGroups(Client client) {
		List<Class<?>> groups = new ArrayList<>();
		
		groups.add(Client.class);
		
		if (isPersonSelected(client)) {
			groups.add(client.getPersonType().getGroup());
		}
		
		return groups;
	}

	private boolean isPersonSelected(Client client) {
		return null != client && null != client.getPersonType();
	}

}
