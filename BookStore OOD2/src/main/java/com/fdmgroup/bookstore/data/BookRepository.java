package com.fdmgroup.bookstore.data;

public interface BookRepository<T, ID> extends Removeable<T>, Persistable<T>, Searchable<T, ID>  {

}