package com.fdmgroup.bookstore.data;

import com.fdmgroup.bookstore.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class UserArrayListRepositoryTest extends UserArrayListRepository {
	
	UserArrayListRepository userArrayListRepository;
    User user1;
    User user2;
    User user3;
    ArrayList<User> users;
    
    @BeforeEach
    public void setUp() {
        user1 = new User();
        user1.setUsername("Stefan");
        user1.setPassword("ghj");
        user1.setUserId(UserArrayListRepository.generateId());

        user2 = new User();
        user2.setUsername("Darrell");
        user2.setPassword("sef");
        user2.setUserId(UserArrayListRepository.generateId());
        users = new ArrayList<>(Arrays.asList(user1, user2));


        user3 = new User();
        user3.setUsername("Davis");
        user3.setPassword("nsr");
        user3.setUserId(UserArrayListRepository.generateId());
}
    
    @Test
    public void validateTest() {
        userArrayListRepository = new UserArrayListRepository(users);

        assertTrue(userArrayListRepository.validate("Stefan", "ghj"));
        assertFalse(userArrayListRepository.validate("Stefan", "rew"));
    }
    
    @Test
    void findByUsernameTest() {
        userArrayListRepository = new UserArrayListRepository(users);
        Assertions.assertEquals(user1, userArrayListRepository.findByUsername("Stefan"));
        Assertions.assertEquals(null, userArrayListRepository.findByUsername("Stefan2"));
    }

    @Test
    void saveTest() {
        userArrayListRepository = new UserArrayListRepository(users);
        assertFalse(userArrayListRepository.validate("StefanNew", "ghj"));
        user1.setUsername("StefanNew");
        user1.setUserId(UserArrayListRepository.generateId());
        User saveReturnedUser1 = userArrayListRepository.save(user1);
        assertEquals(saveReturnedUser1, user1);
        assertTrue(userArrayListRepository.validate("StefanNew", "ghj"));
        assertEquals(2, userArrayListRepository.getUsers().size());

        int newID = UserArrayListRepository.generateId();
        user3.setUserId(newID);
        User saveReturnedUser2 = userArrayListRepository.save(user3);
        assertEquals(user3, saveReturnedUser2);
        userArrayListRepository.validate("Davis", "nsr");
        assertEquals(3, userArrayListRepository.getUsers().size());
    }

    @Test
    void removeTest() {
        userArrayListRepository = new UserArrayListRepository(users);
        userArrayListRepository.delete(user1);

        assertFalse(userArrayListRepository.validate(user1.getUsername(), user1.getPassword()));
        assertEquals(1, userArrayListRepository.getUsers().size());
    }

    @Test
    void generateIDTest() {
        int id = UserArrayListRepository.generateId();
        assertEquals(id + 1, UserArrayListRepository.generateId());
        assertEquals(id + 2, UserArrayListRepository.generateId());
        assertEquals(id + 3, UserArrayListRepository.generateId());
    }

    @Test
    void findByIdTest() {
        userArrayListRepository = new UserArrayListRepository(users);

        Assertions.assertEquals(user1, userArrayListRepository.findById(user1.getUserId()));
        Assertions.assertNotEquals(user2, userArrayListRepository.findById(user1.getUserId()));
        Assertions.assertEquals(user2, userArrayListRepository.findById(user2.getUserId()));
        Assertions.assertEquals(null, userArrayListRepository.findById(user3.getUserId()));
    }

    @Test
    void findAllTest() {
        userArrayListRepository = new UserArrayListRepository();
        assertEquals(null, userArrayListRepository.findAll());
        userArrayListRepository = new UserArrayListRepository(users);
        assertEquals(users, userArrayListRepository.findAll());
    } 
}