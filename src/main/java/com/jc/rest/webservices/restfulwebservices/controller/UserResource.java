package com.jc.rest.webservices.restfulwebservices.controller;

import com.jc.rest.webservices.restfulwebservices.dao.UserDaoService;
import com.jc.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import com.jc.rest.webservices.restfulwebservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    //get detail a specific user
    @GetMapping("/users/{userId}")
    public Resource<User> getUser(@PathVariable int userId) {

        User user = userDaoService.findOne(userId);
        if (user == null) {
            throw new UserNotFoundException("Id" + userId);
        }

        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    //retrieve all users
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {

        return userDaoService.findAll();
    }

    //Response with 201 Created and location header reponse with the URI to get the new user created
    @PostMapping("/users")
    public ResponseEntity.BodyBuilder createUser(@Valid @RequestBody User user) {

        User savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable int userId) {

        User user = userDaoService.deleteById(userId);
        if (user == null) {
            throw new UserNotFoundException("Id" + userId);
        }
    }
}
