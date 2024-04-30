package ma.petpulse.petpulsecore.web;


import lombok.RequiredArgsConstructor;
import ma.petpulse.petpulsecore.service.dtos.AuthenticationRequest;
import ma.petpulse.petpulsecore.service.dtos.AuthenticationResponse;
import ma.petpulse.petpulsecore.service.dtos.RegisterRequest;
import ma.petpulse.petpulsecore.service.services.interfaces.IAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final IAuthenticationService authService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {

        // Handle the case where a user with the same email already exists
        try {
            return ResponseEntity.ok(authService.register(request));
        } catch (IllegalArgumentException e) {
            String message = e.getMessage() != null ? e.getMessage() : "An error occurred during registration";
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
    }

//    @PutMapping
//    public ResponseEntity<AuthenticationResponse> updateCredentials(@RequestBody DTOUserCredentials userCredentials) {
//        return ResponseEntity.ok(authService.updateCredentials(userCredentials));
//    }
}
