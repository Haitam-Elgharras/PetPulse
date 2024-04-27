package ma.petpulse.petpulsecore.service.services.implementations;

import ma.petpulse.petpulsecore.dao.entities.User;
import ma.petpulse.petpulsecore.dao.repositories.UserRepository;
import ma.petpulse.petpulsecore.service.services.interfaces.IUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User User) {
        return userRepository.save(User);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User User) {
        return userRepository.save(User);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("reach for user with email: " + email);
        return userRepository.findByEmail(email)
                .orElse(null);
    }

    // other methods related to user service
}

