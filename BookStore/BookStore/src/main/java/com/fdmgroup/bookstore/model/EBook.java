package com.fdmgroup.bookstore.model;

public class EBook extends Book {

	private double sizeMegaBytes;
	
		public EBook(int itemId, double price, String title, String author, BookGenre bookGenre) {
		super(itemId, price, title, author, bookGenre);
	}

		public double getSizeMegaBytes() {
			return sizeMegaBytes;
		}

		public void setSizeMegaBytes(double sizeMegaBytes) {
			this.sizeMegaBytes = sizeMegaBytes;
		}
};