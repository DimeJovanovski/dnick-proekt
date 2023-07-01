package mk.ukim.finki.dnick.proekt.ebookstore.service;

import mk.ukim.finki.dnick.proekt.ebookstore.model.User;

public interface AuthService {
    User login(String username, String password);
}