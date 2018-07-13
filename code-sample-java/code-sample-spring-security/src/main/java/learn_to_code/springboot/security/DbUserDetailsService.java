package learn_to_code.springboot.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class DbUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public DbUserDetailsService(UserRepository repository) {
        userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUsername(userName).orElseThrow(UserNotFoundException::new);
    }
}
