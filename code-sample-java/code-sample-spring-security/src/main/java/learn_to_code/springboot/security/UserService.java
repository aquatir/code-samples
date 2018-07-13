package learn_to_code.springboot.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /* By the time this check is executed, the authentication object has already been constructed
    (including all the checks such as is user enabled / are credentials expired, etc)
    * thus no password check or anything alike is required*/
    public User getCurrentAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        return userRepository.findByUsername(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
    }
}
