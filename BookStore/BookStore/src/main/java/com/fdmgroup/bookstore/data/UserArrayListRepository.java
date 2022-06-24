package com.fdmgroup.bookstore.data;

import java.util.ArrayList;
import java.util.Objects;

import com.fdmgroup.bookstore.model.User;

public class UserArrayListRepository implements UserRepository {
	private ArrayList<User> users;
	public static int id = 1;
	

	public UserArrayListRepository() {
		super();
	}
	
	public UserArrayListRepository(ArrayList<User> users) {
		super();
		this.users = users;
	}
	
	public static int generateId() {
		return id++;
	}
	public ArrayList<User> getUsers() {
        return users;
    }
	
    @Override
    public void delete(User user) {
        users.remove(user);
    }
    
    @Override
    public boolean validate(String username, String password) {
        return users.stream().anyMatch(user -> (Objects.equals(user.getUsername(), username)
                && Objects.equals(user.getPassword(), password)));
    }

    @Override
    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User save(User user) {
        for (User eachUser : users) {
            if (Objects.equals(eachUser.getUsername(), user.getUsername())) {
                eachUser.setFirstName(user.getUsername());
                eachUser.setLastName(user.getLastName());
                eachUser.setPassword(user.getPassword());
                eachUser.setEmail(user.getEmail());
                eachUser.setOrders(user.getOrders());
                return eachUser;
            }
        }

        users.add(user);
        return user;
    }

    @Override
    public User findById(int id) {
        for (User user : users) {
            if (user.getUserId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public ArrayList<User> findAll() {
        return users;
    }
};