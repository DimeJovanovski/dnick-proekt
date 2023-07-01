package mk.ukim.finki.dnick.proekt.ebookstore.service.impl;

import mk.ukim.finki.dnick.proekt.ebookstore.model.Auction;
import mk.ukim.finki.dnick.proekt.ebookstore.model.User;
import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.AuctionStatus;
import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.BookCondition;
import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.BookType;
import mk.ukim.finki.dnick.proekt.ebookstore.repository.AuctionRepository;
import mk.ukim.finki.dnick.proekt.ebookstore.service.AuctionService;
import mk.ukim.finki.dnick.proekt.ebookstore.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuctionServiceImpl implements AuctionService {
    private final AuctionRepository auctionRepository;
    private final UserService userService;

    public AuctionServiceImpl(AuctionRepository auctionRepository, UserService userService) {
        this.auctionRepository = auctionRepository;
        this.userService = userService;
    }

    @Override
    public Auction create(String title,
                          String author,
                          BookCondition book_condition,
                          BookType book_type,
                          MultipartFile picture,
                          Long price,
                          User seller) {
        Auction auction = new Auction();

        auction.setTitle(title);
        auction.setAuthor(author);
        auction.setBook_condition(book_condition);
        auction.setBook_type(book_type);
        auction.setPrice(price);
        auction.setSeller(seller);
        auction.setStatus(AuctionStatus.PENDING);
        String fileName = StringUtils.cleanPath(picture.getOriginalFilename());
        if(fileName.contains("..")) {
            System.out.println("not a a valid file");
        } try {
            auction.setPicture(Base64.getEncoder().encodeToString(picture.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.auctionRepository.save(auction);
    }

    @Override
    public List<Auction> listAvailableAuctions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<Auction> availableAuctions = this.auctionRepository
                .findAll()
                .stream()
                .filter(auction -> !auction.getSeller().getUsername().equals(username) && auction.getStatus().equals(AuctionStatus.PENDING))
                .collect(Collectors.toList());
        return availableAuctions;
    }

    @Override
    public List<Auction> getMyAuctions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<Auction> myAuctions = this.auctionRepository
                .findAll()
                .stream()
                .filter(auction -> auction.getSeller().getUsername().equals(username))
                .collect(Collectors.toList());
        return myAuctions;
    }
}
