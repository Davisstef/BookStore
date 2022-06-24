package com.fdmgroup.bookstore.data;

import java.util.ArrayList;

import com.fdmgroup.bookstore.model.User;

public interface Searchable {
	User findById(int id);
	
	ArrayList<User> findAll();
}