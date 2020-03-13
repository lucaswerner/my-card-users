package com.mycard.cards.controller;

import com.mycard.cards.dto.PostUserDTO;
import com.mycard.cards.dto.UserDTO;
import com.mycard.cards.entity.User;
import com.mycard.cards.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
@Api(tags = "user")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping
    @ApiOperation(value = "GetUserList")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong")
    })
    public ResponseEntity<List<UserDTO>> getUserList() {
        final List<User> userList = userService.getUserList();
        return ResponseEntity.ok().body(modelMapper.map(userList, new TypeToken<List<UserDTO>>() {
        }.getType()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "GetUser")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 204, message = "Could not find user")
    })
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        final Optional<User> optionalUser = userService.getUser(id);

        return optionalUser
                .map(user -> ResponseEntity.ok().body(this.modelMapper.map(user, UserDTO.class)))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    @ApiOperation(value = "PostUser")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong")
    })
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody PostUserDTO postUserDTO, HttpServletRequest request) {
        final User savedUser = userService.saveUser(modelMapper.map(postUserDTO, User.class));

        final URI location = URI.create(String.format(
                request.getRequestURI() + "/%s",
                savedUser.getId()));

        return ResponseEntity.created(location).body(modelMapper.map(savedUser, UserDTO.class));
    }

    @PutMapping
    @ApiOperation(value = "PutUser")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 409, message = "Unable to find User for update")
    })
    public ResponseEntity<UserDTO> putUser(@Valid @RequestBody UserDTO userDTO) {
        final Optional<User> optionalUser = userService.updateUser(modelMapper.map(userDTO, User.class));

        return optionalUser
                .map(user -> ResponseEntity.ok().body(modelMapper.map(user, UserDTO.class)))
                .orElseGet(() -> ResponseEntity.status(409).build());
    }

}
