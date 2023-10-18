package be.ipl.spring.wishlists;

import be.ipl.spring.wishlists.models.Video;
import be.ipl.spring.wishlists.models.Wishlist;
import be.ipl.spring.wishlists.repositories.VideosProxy;
import be.ipl.spring.wishlists.repositories.WishlistsRepository;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class WishlistsService {

    private final WishlistsRepository repository;
    private final VideosProxy videosProxy;

    public WishlistsService(WishlistsRepository repository, VideosProxy videosProxy) {
        this.repository = repository;
        this.videosProxy = videosProxy;
    }

    /**
     * Creates a review in repository
     * @param  wishlist to create
     * @return true if the review was created, false if another review exists with the same pseudo and hash
     */
    public boolean createOne(Wishlist wishlist) {
        if (repository.existsById(wishlist.getId())) {
            return false;
        }
        repository.save(wishlist);
        return true;
    }

    /**
     * Reads a review in repository
     * @param pseudo Pseudo of the user reviewing
     * @return The review, or null if the review couldn't be found
     */
    public Wishlist readOne(String pseudo) {
        return repository.findByUser(pseudo).orElse(null);
    }

    /**
     * Deletes a review from repository
     * @param pseudo Pseudo of the user reviewing
     * @return true if the review was deleted, or false if the review couldn't be found
     */
    public boolean deleteOne(String pseudo) {
        if (repository.findByUser(pseudo).isEmpty()) return false;
        repository.deleteByUser(pseudo);
        return true;
    }


    /**
     * Reads all reviews from a user
     * @param pseudo Pseudo of the user
     * @return The list of reviews from this user
     */
    public Optional<Wishlist> readFromUser(String pseudo) {
        return repository.findByUser(pseudo);
    }

    /**
     * Deletes all reviews from a user
     * @param pseudo Pseudo of the user
     */
    public void deleteFromUser(String pseudo) {
        repository.deleteByUser(pseudo);
    }

    /**
     * Deletes all reviews from a user
     * @param product Pseudo of the user
     */
    public void deleteFromProduct(int product) {
        repository.deleteByProduct(product);
    }
}
