package com.jc.rest.webservices.restfulwebservices.controller;

import com.jc.rest.webservices.restfulwebservices.exceptions.UserNotFoundException;
import com.jc.rest.webservices.restfulwebservices.model.Post;
import com.jc.rest.webservices.restfulwebservices.model.User;
import com.jc.rest.webservices.restfulwebservices.repository.PostRepository;
import com.jc.rest.webservices.restfulwebservices.repository.UserRepository;
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
import java.util.Optional;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    //get detail a specific user
    @GetMapping("/jpa/users/{userId}")
    public Resource<User> getUser(@PathVariable int userId) {

        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("Id" + userId);
        }

        Resource<User> resource = new Resource<User>(user.get());
        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    //retrieve all users
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {

        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{userId}/posts")
    public List<Post> retrieveAllUserPosts(@PathVariable int userId) {

        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("Id: " + userId);
        }
        return user.get().getPosts();
    }

    //Response with 201 Created and location header reponse with the URI to get the new user created
    @PostMapping("/jpa/users")
    public ResponseEntity.BodyBuilder createUser(@Valid @RequestBody User user) {

        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location);
    }

    //Response with 201 Created and location header reponse with the URI to get the new user created
    @PostMapping("/jpa/users/{userId}/posts")
    public ResponseEntity.BodyBuilder createPost(@PathVariable int userId, @RequestBody Post post) {

        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("Id:" + userId);
        }

        User user = userOptional.get();
        post.setUser(user);
        postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location);
    }

    @DeleteMapping("/jpa/users/{userId}")
    public void deleteUser(@PathVariable int userId) {

        userRepository.deleteById(userId);
    }
}
