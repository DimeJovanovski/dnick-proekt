package mk.ukim.finki.dnick.proekt.ebookstore.model.exceptions;

public class InvalidAuctionIdException extends RuntimeException {
    public InvalidAuctionIdException(Long id) {
        super(String.format("Auction with id %d does not exist", id));
    }
}
