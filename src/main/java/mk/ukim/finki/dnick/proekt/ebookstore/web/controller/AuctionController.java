package mk.ukim.finki.dnick.proekt.ebookstore.web.controller;

import mk.ukim.finki.dnick.proekt.ebookstore.model.Auction;
import mk.ukim.finki.dnick.proekt.ebookstore.model.User;
import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.BookCondition;
import mk.ukim.finki.dnick.proekt.ebookstore.model.enumerations.BookType;
import mk.ukim.finki.dnick.proekt.ebookstore.service.AuctionService;
import mk.ukim.finki.dnick.proekt.ebookstore.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping({"/", "/auctions"})
public class AuctionController {
    private final AuctionService auctionService;
    private final UserService userService;

    public AuctionController(AuctionService auctionService, UserService userService) {
        this.auctionService = auctionService;
        this.userService = userService;
    }

    @GetMapping
    public String getAuctionsPage(Model model) {
        List<Auction> availableAuctions = this.auctionService.listAvailableAuctions();
        model.addAttribute("availableAuctions", availableAuctions);
        model.addAttribute("bodyContent","auctions");

        return "master-template";
    }

    @GetMapping("/my-auctions")
    public String getUsersAuctions(Model model) {
        List<Auction> myAuctions = this.auctionService.getMyAuctions();
        model.addAttribute("auctions", myAuctions);
        model.addAttribute("bodyContent","my-auctions");

        return "master-template";
    }

    @GetMapping("/bought")
    public String getBoughtAuctions(Model model) {
        List<Auction> boughtAuctions = this.auctionService.getBoughtAuctions();
        model.addAttribute("auctions", boughtAuctions);
        model.addAttribute("bodyContent","bought-auctions");

        return "master-template";
    }

    @PostMapping("auctions/buy/{id}")
    public String buyAuction(@PathVariable Long id) {
        this.auctionService.buy(id);

        return "redirect:/auctions";
    }

    @GetMapping("/create-form")
    public String getCreateAuctionForm(Model model) {
        BookCondition[] book_conditions = BookCondition.values();
        BookType[] book_types = BookType.values();

        model.addAttribute("book_conditions", book_conditions);
        model.addAttribute("book_types", book_types);
        model.addAttribute("bodyContent","create-auction");

        return "master-template";
    }

    @PostMapping("/add")
    public String addAuction(HttpServletRequest req,
                             @RequestParam String title,
                             @RequestParam String author,
                             @RequestParam BookCondition book_condition,
                             @RequestParam BookType book_type,
                             @RequestParam("picture") MultipartFile picture,
                             @RequestParam Long price) {
        String username = req.getRemoteUser();
        User user = this.userService.findByUsername(username);
        this.auctionService.create(title, author, book_condition, book_type, picture, price, user);
        return "redirect:/auctions";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAuction(@PathVariable Long id) {
        Auction auction = this.auctionService.findById(id);
        this.auctionService.delete(auction);

        return "redirect:/auctions";
    }
}
