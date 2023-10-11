package be.ipl.spring.wishlists.repositories;

import be.ipl.spring.wishlists.models.Wishlist;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishlistsRepository extends CrudRepository<Wishlist, Long> {

    boolean existsByPseudoAndHash(String pseudo, String hash);
    Optional<Wishlist> findByPseudoAndHash(String pseudo, String hash);

    @Transactional
    void deleteByPseudoAndHash(String pseudo, String hash);

    @Transactional
    void deleteByPseudo(String pseudo);

    @Transactional
    void deleteByHash(String hash);

    Iterable<Wishlist> findByPseudo(String pseudo);
    Iterable<Wishlist> findByHash(String hash);

}
