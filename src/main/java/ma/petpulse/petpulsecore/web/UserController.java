package ma.petpulse.petpulsecore.web;

import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.exceptions.RequiredFieldMissingException;
import ma.petpulse.petpulsecore.exceptions.UserNotFoundException;
import ma.petpulse.petpulsecore.service.dtos.UserDto;
import ma.petpulse.petpulsecore.service.mappers.UserMapper;
import ma.petpulse.petpulsecore.service.services.interfaces.IUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Transactional
@EnableMethodSecurity
@EnableWebSecurity
public class UserController {

    private final IUserService userService;
    private final UserMapper userMapper;

    public UserController(IUserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    @PreAuthorize("hasRole('PET_OWNER')")
    public List<User> getAllUsers() {
        return userService.getAllUsers().stream().map(
                user -> {
                    user.setPassword(null);
                    return user;
                }
        ).toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('PET_OWNER')")
    public User getUserById(@PathVariable Long id) {
        // check if user exists
        User user = userService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        return user;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserDto addUser(@RequestBody User user) {
        // check if the required fields are not empty
        if (user.getFirstName() == null || user.getEmail() == null || user.getPassword() == null) {
            // custom exception with the name RequiredFieldMissingException
            throw new RequiredFieldMissingException("Required fields are missing");
        }
        if(user.getRole() != null)
            user.setRole(null);

        return userMapper.userToUserDto(userService.addUser(user));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        // check if user exists
        User userAccount = userService.getUserById(id);
        if (userAccount == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        userService.deleteUserById(id);
    }
}