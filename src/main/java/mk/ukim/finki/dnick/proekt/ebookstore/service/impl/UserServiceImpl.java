package mk.ukim.finki.dnick.proekt.ebookstore.service.impl;

import mk.ukim.finki.dnick.proekt.ebookstore.model.User;
import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.Role;
import mk.ukim.finki.dnick.proekt.ebookstore.model.exceptions.InvalidUsernameException;
import mk.ukim.finki.dnick.proekt.ebookstore.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.dnick.proekt.ebookstore.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.dnick.proekt.ebookstore.repository.UserRepository;
import mk.ukim.finki.dnick.proekt.ebookstore.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidUsernameException());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.userRepository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException(username));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getPassword(),
                Stream.of(new SimpleGrantedAuthority(u.getRole().toString())).collect(Collectors.toList()));

        return userDetails;
    }

    @Override
    public User register(String username, String phone_number, String email, String password, Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username, phone_number, email, passwordEncoder.encode(password), role);
        return userRepository.save(user);
    }



}
