package mk.ukim.finki.dnick.proekt.ebookstore.service;

import mk.ukim.finki.dnick.proekt.ebookstore.model.Auction;
import mk.ukim.finki.dnick.proekt.ebookstore.model.User;
import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.BookCondition;
import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.BookType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AuctionService {
    Auction create(String title,
                   String author,
                   BookCondition book_condition,
                   BookType book_type,
                   MultipartFile picture,
                   Long price,
                   User seller);

    List<Auction> listAvailableAuctions();

    List<Auction> getMyAuctions();
    List<Auction> getBoughtAuctions();

    Auction findById(Long id);

    Auction buy(Long auctionId);

    void delete(Auction auction);
}
