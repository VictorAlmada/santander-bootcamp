package me.dio.domain.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.domain.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	// atributos
	private final UserRepository userRepository;
	
	// construtores
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// métodos
	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public User create(User userToCreate) {
		if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
			throw new IllegalArgumentException("This account number already exists.");
		}
		return userRepository.save(userToCreate);
	}

}
