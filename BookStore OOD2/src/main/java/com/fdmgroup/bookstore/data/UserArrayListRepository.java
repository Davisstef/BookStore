/* package com.fdmgroup.bookstore.data;

import java.util.ArrayList;
import com.fdmgroup.bookstore.model.User;

public class UserArrayListRepository implements UserRepository {
	private ArrayList<User> users;
	public static int id = 1;
	

	public UserArrayListRepository() {}
	
	public UserArrayListRepository(ArrayList<User> users) {
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
		for(User user: users) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) 
				return true;
		}
		return false;
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
		User existingUser = null;
		for (User userInList: users) {
			if (userInList.getUserId() == user.getUserId()) {
				existingUser = userInList;
			}
		}
		if( existingUser != null) {
			//overwrite the existing user
			existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setUsername(user.getUsername());
			existingUser.setPassword(user.getPassword());
			existingUser.setEmail(user.getEmail());
			existingUser.setOrders(user.getOrders());
			return existingUser;
		} else {
			int newId = generateId();
			user.setUserId(newId);
			users.add(user);
			return user;
		}
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
*/