package org.example.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.dto.RequestUser;
import org.example.dto.ResponseUser;
import org.example.service.UserService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RequestMapping("api/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseUser saveUser(@Valid @RequestBody RequestUser user) {
        var user1 = userService.saveUser(user);
        return user1;
    }

    @GetMapping("/{id}")
    public EntityModel<ResponseUser> getUser(@PathVariable Integer id) {
        var user1 = userService.getUser(id);
        EntityModel<ResponseUser> model = EntityModel.of(user1);
        model.add(linkTo(methodOn(UserController.class).getUser(id)).withSelfRel());
        model.add(linkTo(methodOn(UserController.class).updateUser(id, null)).withRel("update"));
        model.add(linkTo(methodOn(UserController.class).deleteUser(id)).withRel("delete"));
        return model;
    }

    @DeleteMapping("/{id}")
    public ResponseUser deleteUser(@PathVariable Integer id) {
        var user1 = userService.deleteUser(id);
        return user1;
    }

    @PutMapping("/{id}")
    public EntityModel<ResponseUser> updateUser(@PathVariable Integer id, @Valid @RequestBody RequestUser user) {
        var user1 = userService.updateUser(id, user);
        EntityModel<ResponseUser> model = EntityModel.of(user1);
        model.add(linkTo(methodOn(UserController.class).getUser(id)).withSelfRel());
        model.add(linkTo(methodOn(UserController.class).updateUser(id, null)).withRel("update"));
        model.add(linkTo(methodOn(UserController.class).deleteUser(id)).withRel("delete"));
        return model;
    }
}
