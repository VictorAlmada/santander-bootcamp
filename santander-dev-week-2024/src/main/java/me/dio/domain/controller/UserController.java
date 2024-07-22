package me.dio.domain.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.dio.domain.model.User;
import me.dio.domain.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	// atributos
	private final UserService userService;
	
	// contrutores
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// métodos
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		var user = userService.findById(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User userToCreate) {
		var userCreated = userService.create(userToCreate);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(userCreated.getId())
				.toUri();
		return ResponseEntity.created(location).body(userCreated);
	}

}
