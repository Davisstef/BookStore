package com.fdmgroup.bookstore.data;

import java.util.List;

public interface Searchable<T, ID> {
	public T findById(int id);
	
	public List<T> findAll();
}