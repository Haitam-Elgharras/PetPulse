package ma.petpulse.petpulsecore.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.exceptions.UserNotFoundException;
import ma.petpulse.petpulsecore.service.dtos.UserDto;
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
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping()
    @PreAuthorize("hasRole('PET_OWNER')")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('PET_OWNER')")
    public User getUserById(@PathVariable Long id) {
        // check if user exists
        User user = userService.getUserById(id);
        if (user == null)
            throw new UserNotFoundException("User with id " + id + " not found");

        return user;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserDto addUser(@Valid @RequestBody User user) {
        if(user.getRole() != null)
            user.setRole(null);

        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        // check if user exists
        User user = userService.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        userService.deleteUserById(id);
    }


    // exception handling
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        String errorMessage = ex.getBindingResult().getAllErrors().stream()
//                .map(ObjectError::getDefaultMessage)
//                .collect(Collectors.joining(", "));
//        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
//    }
}