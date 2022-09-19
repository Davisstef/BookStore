package com.fdmgroup.bookstore.data;

import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
import com.fdmgroup.bookstore.model.Order;
import com.fdmgroup.bookstore.model.User;
import com.fdmgroup.bookstore.service.AuthenticationService;
import com.fdmgroup.bookstore.service.BookService;
import exceptions.ItemNotFoundException;
import exceptions.UserNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class OrderingServiceTest {
	
	public Book book1 = new Book(1, 250.0, "mybook", "Stefan Davis", BookGenre.NONFICTION);
	public Book book2 = new Book(2, 251.0, "mybook1", "Bill Nye", BookGenre.NONFICTION);
	public Book book3 = new Book(3, 252.0, "mybook2", "Neil DeGrasse Tyson", BookGenre.NONFICTION);
	public Book book4 = new Book(4, 252.0, "mybook", "Justin Trudeau", BookGenre.NONFICTION);

	public List<Book> booklist = new ArrayList<Book>();
	public User user1 = new User(1, "first", "last", "firstlast", "mypass", "myemail", new ArrayList<Order>());
	public User user2 = new User(2, "first1", "last1", "firstlast1", "mypass1", "myemail1", new ArrayList<Order>());
	public List<Order> orderlist = new ArrayList<Order>();


	@Test
	public void placeOrderTest() throws ItemNotFoundException, UserNotFoundException {
		booklist.add(book1);
		booklist.add(book2);
		booklist.add(book3);
		OrderRepository mockorderRepository = (mock(OrderRepository.class));
		BookRepository mockbookRepository = mock(BookRepository.class);
		BookService bookService = new BookService(mockbookRepository);
		UserRepository mockuserRepository = mock(UserRepository.class);
		AuthenticationService authenticationService = new AuthenticationService(mockuserRepository);
		when(mockbookRepository.findById(1)).thenReturn(booklist.get(0));
		bookService.findById(1);
		when(mockuserRepository.findById(1)).thenReturn(user1);
		authenticationService.findById(1);
		Order order1 = new Order(book1.getItemId(), book1, user1, LocalDateTime.MAX);
		when(mockorderRepository.save(order1)).thenReturn(order1);
		assertThrows(ItemNotFoundException.class,() -> bookService.findById(7));
		assertThrows(UserNotFoundException.class,() -> authenticationService.findById(9));
	}
	
	@Test
	public void placeOrdersTest() throws ItemNotFoundException, UserNotFoundException {
		booklist.add(book1);
		booklist.add(book2);
		BookRepository mockbookRepository = mock(BookRepository.class);
		BookService bookService = new BookService(mockbookRepository);
		UserRepository mockuserRepository = mock(UserRepository.class);
		AuthenticationService authenticationService = new AuthenticationService(mockuserRepository);
		when(mockbookRepository.findById(1)).thenReturn(booklist.get(0));
		when(mockbookRepository.findById(2)).thenReturn(booklist.get(1));
		bookService.findById(1);
		bookService.findById(2);
		when(mockuserRepository.findById(1)).thenReturn(user1);
		authenticationService.findById(1);
		Order order1 = new Order(book1.getItemId(), book1, user1, LocalDateTime.MAX);
		Order order2 = new Order(book2.getItemId(), book2, user1, LocalDateTime.MAX);
		List<Order> actualresult = new ArrayList<Order>();
		actualresult.add(order1);
		actualresult.add(order2);
		assertThrows(ItemNotFoundException.class,() -> bookService.findById(7));
		assertThrows(UserNotFoundException.class,() -> authenticationService.findById(9));
	}
	
	@Test
	public void getOrdersForUserTest() {
		booklist.add(book1);
		booklist.add(book2);
		OrderRepository mockorderRepository = (mock(OrderRepository.class));
		Order order1 = new Order(book1.getItemId(), book1, user1, LocalDateTime.MAX);
		Order order2 = new Order(book2.getItemId(), book2, user1, LocalDateTime.MAX);
		when(mockorderRepository.findAll()).thenReturn(orderlist);
		List<Order> expectedList = new ArrayList<Order>();
		expectedList.add(order1);
		expectedList.add(order2);
	}

	@Test
	public void getOrdersForBookTest() {
		booklist.add(book1);
		booklist.add(book2);
		OrderRepository mockorderRepository = (mock(OrderRepository.class));
		Order order1 = new Order(book1.getItemId(), book1, user1, LocalDateTime.MAX);
		Order order2 = new Order(book2.getItemId(), book2, user1, LocalDateTime.MAX);
		when(mockorderRepository.findAll()).thenReturn(orderlist);
		List<Order> expectedList = new ArrayList<Order>();
		expectedList.add(order1);
	}
}