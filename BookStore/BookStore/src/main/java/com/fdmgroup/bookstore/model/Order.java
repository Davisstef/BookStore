package com.fdmgroup.bookstore.model;

import java.util.Date;

public class Order {
	private int orderId;
	private Book bookOrdered;
	private User user;
	private Date createTime;
	private Date updateTime;
	private Date deleteTime;

	
	public Order(int orderId, Book bookOrdered, User user, Date createTime, Date updateTime, Date deleteTime) {
		super();
		this.orderId = orderId;
		this.bookOrdered = bookOrdered;
		this.user = user;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.deleteTime = deleteTime;
	}
	
	
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public Book getBookOrdered() {
		return bookOrdered;
	}
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public void markCreate() {
        this.updateTime = this.createTime = new Date();
    	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public void markUpdate() {
	        this.updateTime = new Date();
	}
	 
	public Date getDeleteTime() {
		return deleteTime;
	}
	
	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}
	
	public boolean isDeleted() {
        return deleteTime != null;
    	}
	
	public void markDelete() {
        this.deleteTime = new Date();
    	}
}
