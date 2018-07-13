package learn_to_code.springboot.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findAndCheckPassword(UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        if (user.getPassword().equals(userDetails.getPassword())) {
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }
}
