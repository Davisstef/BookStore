package com.fdmgroup.bookstore.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import com.fdmgroup.bookstore.data.BookRepository;
import com.fdmgroup.bookstore.data.OrderRepository;
import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;
import exceptions.ItemNotFoundException;
import exceptions.UserNotFoundException;

public class OrderingService {
	
	private BookRepository<Book, Integer> bookRepository;
	private OrderRepository<Order, Integer> orderRepository;
	private BookService bookService;
	private AuthenticationService authenticationService;
	
	public OrderingService(BookRepository bookRepository, OrderRepository orderRepository, BookService bookService, AuthenticationService authenticationService) {
		super();
		this.orderRepository = orderRepository;
		this.bookRepository = bookRepository;
		this.bookService = bookService;
		this.authenticationService = authenticationService;
	}
	
	public OrderingService() {
		super();
	}
	
	public Order placeOrder(Book book, User customer) throws ItemNotFoundException, UserNotFoundException {
		int orderId = book.hashCode();
		Order order = new Order(orderId, book, customer, LocalDateTime.now());
		
		if (bookService.findById(book.getItemId()) == null) {
			throw new ItemNotFoundException("Book not found.");
		}
		if (authenticationService.findById(customer.getUserId()) == null) {
			throw new UserNotFoundException("User not found.");
		}
		if (bookService.findById(book.getItemId()) != null && authenticationService.findById(customer.getUserId()) == null) {
			throw new UserNotFoundException("User not found.");
		}
		if (bookService.findById(book.getItemId()) == null && authenticationService.findById(customer.getUserId()) != null) {
			throw new ItemNotFoundException("Book not found.");
		}
		
		return orderRepository.save(order);
	}

	
	public List<Order> placeOrders(List<Book> books, User customer) throws ItemNotFoundException, UserNotFoundException {
		List<Order> orders = new ArrayList<>();
		for (Book book: books) {
			if (bookService.findById(book.getItemId()) == null) {
				throw new ItemNotFoundException("A book was not found.");
			}

			if (authenticationService.findById(customer.getUserId()) == null) {
				throw new UserNotFoundException("User not found.");
			}
			
			if (bookService.findById(book.getItemId()) != null && authenticationService.findById(customer.getUserId()) == null) {
				throw new UserNotFoundException("User not found.");
			}
			
			if (bookService.findById(book.getItemId()) == null && authenticationService.findById(customer.getUserId()) != null) {
				throw new ItemNotFoundException("A book was not found.");
			}
		}
		return orders;
	}
	
	public List<Order> getOrdersForUser(User user){
		List<Order> allOrders = orderRepository.findAll();
		List<Order> orderofUser = new ArrayList<>();
		
		for(Order order: allOrders) {
			if(order.getUser().equals(user)) {
				orderofUser.add(order);
			}
		}
		return orderofUser;	
	}
	
	public List<Order> getOrdersForBook(Book book) {
		List<Order> allOrders = orderRepository.findAll();
		List<Order> orderofBook = new ArrayList<>();
		
		for(Order order: allOrders) {
			if(order.getBookOrdered().equals(book)) {
				orderofBook.add(order);
			}
		}	
		return orderofBook;
	}
}