package com.richard.brewer.service;

import com.richard.brewer.repository.Users;

public enum StatusUser {
	
	ACTIVATE {
		@Override
		public void execute(Long[] codes, Users users) {
			users.findByCodeIn(codes).forEach(u -> u.setActive(true));
		}
	},	
	
	DISABLE {
		@Override
		public void execute(Long[] codes, Users users) {
			users.findByCodeIn(codes).forEach(u -> u.setActive(false));
		}
	};
	
	
	public  abstract void execute(Long[]codes, Users users);

}
