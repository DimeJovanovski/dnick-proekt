package mk.ukim.finki.dnick.proekt.ebookstore.service;

import mk.ukim.finki.dnick.proekt.ebookstore.model.User;
import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    User register(String username, String phone_number, String email, String password, Role role);
}
