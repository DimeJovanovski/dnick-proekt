package mk.ukim.finki.dnick.proekt.ebookstore.repository;

import mk.ukim.finki.dnick.proekt.ebookstore.model.Auction;
import mk.ukim.finki.dnick.proekt.ebookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
