package com.fdmgroup.bookstore.service;

import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
import exceptions.ItemNotFoundException;

import java.util.List;
import java.util.ArrayList;

public class BookService {
	
    private BookRepository<Book, Integer> bookRepository;

    public BookService(BookRepository<Book, Integer> bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}
    
    public BookService() {
		super();
	}

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksOfGenre(BookGenre bookGenre) {
    	List<Book> books = new ArrayList<>();
		for (Book book : getAllBooks()) {
			if (book.getBookGenre().equals(bookGenre)) {
				books.add(book);
			}
		}
		return books;
	}

    public List<Book> searchBooksByTitle(String title) {
    	List<Book> books = new ArrayList<>();
		for (Book book : getAllBooks()) {
			if (book.getTitle().equals(title)) {
				books.add(book);
			}
		}
		return books;
	}

    public List<Book> searchBooksByAuthorName(String bookAuthorNameToSearch) {
    	List<Book> books = new ArrayList<>();
		for (Book book : getAllBooks()) {
			if (book.getAuthor().equals(bookAuthorNameToSearch)) {
				books.add(book);
			}
		}
		return books;
	}

    public Book findById(int id) throws ItemNotFoundException {
        if (bookRepository.findById(id) != null) {
			return bookRepository.findById(id);
		}
            throw new ItemNotFoundException("id :" + id + ": not found");
        }
}