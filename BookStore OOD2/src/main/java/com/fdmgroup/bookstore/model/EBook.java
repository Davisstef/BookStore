package com.fdmgroup.bookstore.model;

import java.util.Objects;

public class EBook extends Book {
	private double sizeMegaBytes;
	
	public EBook(int itemId, double price, String title, String author, BookGenre bookGenre, double sizeMegaBytes) {
	super(itemId, price, title, author, bookGenre);
	this.sizeMegaBytes = sizeMegaBytes;
	}

	public double getSizeMegaBytes() {
		return sizeMegaBytes;
	}

	public void setSizeMegaBytes(double sizeMegaBytes) {
		this.sizeMegaBytes = sizeMegaBytes;
	}
	
	@Override
    public String toString() {
        return "EBook{" +
                "sizeMegaBytes=" + sizeMegaBytes +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object i) {
        if (this == i) return true;
        if (i == null || getClass() != i.getClass()) return false;
        if (!super.equals(i)) return false;
        EBook eBook = (EBook) i;
        return Double.compare(eBook.sizeMegaBytes, sizeMegaBytes) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sizeMegaBytes);
    }
}
