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

    boolean existsById(Long id);

    Optional<Wishlist> findByUser(String userPseudo);

    @Transactional
    void deleteById(Long id);

    @Transactional
    void deleteByUser(String pseudo);

    @Transactional
    void deleteByProduct(int product);

}
