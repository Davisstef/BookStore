package com.fdmgroup.bookstore.data;

import com.fdmgroup.bookstore.model.Book;
import com.fdmgroup.bookstore.model.BookGenre;
import com.fdmgroup.bookstore.service.BookService;

import exceptions.ItemNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {
    BookService bookService;
    BookRepository mockBookRepository;

    @BeforeEach
    public void setUp() {
        mockBookRepository = mock(BookRepository.class);
        bookService = new BookService(mockBookRepository);
    }

    @Test
    public void getAllBooksTest() {
        Book book1 = new Book();
        Book book2 = new Book();
        ArrayList<Book> books = new ArrayList<>(Arrays.asList(book1, book2));
        when(mockBookRepository.findAll()).thenReturn(books);
        List<Book> returnBooks = bookService.getAllBooks();
        verify(mockBookRepository, times(1)).findAll();
        assertEquals(books, returnBooks);
    }

    @Test
    public void getBooksOfGenreTest() {
        Book book1 = new Book();
        book1.setBookGenre(BookGenre.NONFICTION);
        Book book2 = new Book();
        book2.setBookGenre(BookGenre.NONFICTION);
        ArrayList<Book> books = new ArrayList<>(Arrays.asList(book1, book2));
        when(mockBookRepository.findAll()).thenReturn(books);
        List<Book> returnedBooks = bookService.getBooksOfGenre(BookGenre.NONFICTION);
        verify(mockBookRepository, times(1)).findAll();
        assertEquals(books, returnedBooks);
    }

    @Test
    public void searchBooksByTitleTest() {
        String title1 = "title1";
        Book book1 = new Book();
        book1.setTitle(title1);
        Book book2 = new Book();
        book2.setTitle(title1);
        ArrayList<Book> expectedBooks = new ArrayList<>(Arrays.asList(book1, book2));
        when(mockBookRepository.findAll()).thenReturn(expectedBooks);
        List<Book> returnedBooks = bookService.searchBooksByTitle(title1);
        verify(mockBookRepository, times(1)).findAll();
        assertEquals(expectedBooks, returnedBooks);
    }

    @Test
    public void searchBooksByAuthorNameTest() {
        Book book1 = new Book();
        String author = "author";
        book1.setAuthor(author);
        ArrayList<Book> books = new ArrayList<>(Arrays.asList(book1));
        when(mockBookRepository.findAll()).thenReturn(books);
        List<Book> returnedBook = bookService.searchBooksByAuthorName(author);
        verify(mockBookRepository, times(1)).findAll();
        assertEquals(books, returnedBook);
    }

    @Test
    public void findByIdTest() throws ItemNotFoundException {
        Book book1 = new Book();
        book1.setItemId(1);
        List<Book> books = new ArrayList<>();
        books.add(book1);
        when(mockBookRepository.findById(1)).thenReturn(book1);
        Book returnedBook = bookService.findById(1);
        assertEquals(book1, returnedBook);
        assertThrows(ItemNotFoundException.class,() -> bookService.findById(3));
    }
}