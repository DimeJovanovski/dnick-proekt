package mk.ukim.finki.dnick.proekt.ebookstore.model;

import lombok.Data;
import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.AuctionStatus;
import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.BookCondition;
import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.BookType;

import javax.persistence.*;

@Entity
@Data
@Table(name = "store_auction")
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    @Enumerated(value = EnumType.STRING)
    private BookCondition book_condition;

    @Enumerated(value = EnumType.STRING)
    private BookType book_type;

    @Lob
    private String picture;

    @Enumerated(value = EnumType.STRING)
    private AuctionStatus status;

    private Long price;

    @ManyToOne
    private User seller;

    @ManyToOne
    private User buyer;

    public Auction(String title,
                   String author,
                   BookCondition book_condition,
                   BookType book_type,
                   String picture,
                   Long price,
                   User seller) {
        this.title = title;
        this.author = author;
        this.book_condition = book_condition;
        this.book_type = book_type;
        this.picture = picture;
        this.status = AuctionStatus.PENDING;
        this.price = price;
        this.seller = seller;
    }

    public Auction() {

    }
}