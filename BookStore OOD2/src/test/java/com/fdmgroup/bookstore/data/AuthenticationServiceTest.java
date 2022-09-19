package com.fdmgroup.bookstore.data;

import com.fdmgroup.bookstore.model.User;
import com.fdmgroup.bookstore.service.AuthenticationService;

import exceptions.UserNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {
	AuthenticationService authenticationService;
	UserRepository userRepository;
	
	@BeforeEach
	public void init() {
		userRepository = mock(UserRepository.class);
		authenticationService = new AuthenticationService(userRepository);
	}

    @Test
    public void authenticateTest() throws UserNotFoundException {
        User user = new User();
        user.setUsername("Stefan");
        user.setPassword("gef");
        when(userRepository.validate(user.getUsername(), user.getPassword())).thenReturn(true);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        User returnedUser = authenticationService.authenticate(user.getUsername(), user.getPassword());
        verify(userRepository, times(1)).validate(user.getUsername(), user.getPassword());
        assertEquals(user, returnedUser);
        assertThrows(UserNotFoundException.class, () -> authenticationService.authenticate("Wrong name", user.getPassword()));
    }


    @Test
    public void findByIdTest() throws UserNotFoundException {
        User user = new User();
        user.setUsername("Davis");
        user.setPassword("few");
        user.setUserId(1);
        when(userRepository.findById(user.getUserId())).thenReturn(user);
        User returnedUser = authenticationService.findById(user.getUserId());
        assertEquals(returnedUser, user);
        assertThrows(UserNotFoundException.class, () -> authenticationService.findById(2));
    }
}