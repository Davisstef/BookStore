package com.fdmgroup.bookstore.model;

import java.util.Objects;

public class Book {
	private int itemId;
	private double price;
	private String title;
	private String author;
	private BookGenre bookGenre;
	
	public Book() {}
	
	public Book(int itemId, double price, String title, String author, BookGenre bookGenre) {
		super();
		this.itemId = itemId;
		this.price = price;
		this.title = title;
		this.author = author;
		this.bookGenre = bookGenre;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public BookGenre getBookGenre() {
		return bookGenre;
	}
	
	public void setBookGenre(BookGenre bookGenre) {
		this.bookGenre = bookGenre;
	}
	
    @Override
    public String toString() {
        return "Book{" +
                "itemId=" + itemId +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", bookGenre=" + bookGenre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return itemId == book.itemId && Double.compare(book.price, price) == 0 && Objects.equals(title, book.title)
                && Objects.equals(author, book.author) && bookGenre == book.bookGenre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, price, title, author, bookGenre);
    }
}


