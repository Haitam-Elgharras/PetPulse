package ma.petpulse.petpulsecore.service.services.interfaces;

import ma.petpulse.petpulsecore.dao.entities.Pet;
import ma.petpulse.petpulsecore.dao.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService extends UserDetailsService {

    User loadUserByUsername(String username) throws UsernameNotFoundException;

    User addUser(User User);
    void deleteUserById(long id);
    User updateUser(User User);

    User getUserById(long id);
    List<User> getAllUsers();

    User getUserByEmail(String email);

    List<Pet> getAllPets(User user);
}
