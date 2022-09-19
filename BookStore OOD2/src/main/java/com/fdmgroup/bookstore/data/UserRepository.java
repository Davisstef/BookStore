package com.fdmgroup.bookstore.data;

import com.fdmgroup.bookstore.model.User;

public interface UserRepository<T, ID> extends Searchable<T, ID>{
	
	boolean validate(String username, String password);
	
	User findByUsername(String username);
}