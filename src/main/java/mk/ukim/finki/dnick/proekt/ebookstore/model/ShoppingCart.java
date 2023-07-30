//package mk.ukim.finki.dnick.proekt.ebookstore.model;
//
//import lombok.Data;
//import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.ShoppingCartStatus;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Data
//@Table(name = "shopping_cart")
//public class ShoppingCart {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    private LocalDateTime date_created;
//
//    @ManyToOne
//    private User user;
//
//    @ManyToMany
//    private List<Auction> products;
//
//    @Enumerated(EnumType.STRING)
//    private ShoppingCartStatus status;
//
//    public ShoppingCart() {
//        this.products = new ArrayList<>();
//    }
//
//    public ShoppingCart(User user) {
//        this.user = user;
//        this.products = new ArrayList<>();
//        this.date_created = LocalDateTime.now();
//        this.status = ShoppingCartStatus.CREATED;
//    }
//}
