package mk.ukim.finki.dnick.proekt.ebookstore.model;

import lombok.Data;
import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.Role;

import javax.persistence.*;

@Entity
@Data
@Table(name = "store_user")
public class User {
    @Id
    private String username;

    private String phone_number;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String username, String phone_number, String email, String password, Role role) {
        this.username = username;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}