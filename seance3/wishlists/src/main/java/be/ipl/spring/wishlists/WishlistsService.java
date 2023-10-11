package be.ipl.spring.wishlists;

import be.ipl.spring.wishlists.models.Video;
import be.ipl.spring.wishlists.models.Wishlist;
import be.ipl.spring.wishlists.repositories.VideosProxy;
import be.ipl.spring.wishlists.repositories.WishlistsRepository;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

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
     * @param hash Hash of the video being reviewed
     * @return The review, or null if the review couldn't be found
     */
    public Wishlist readOne(String pseudo, String hash) {
        return repository.findByPseudoAndHash(pseudo, hash).orElse(null);
    }

    /**
     * Updates a review in repository
     * @param newReview New values of the review
     * @return true if the review was updated, or false if the review couldn't be found
     */
    public boolean updateOne(Wishlist newWishlist) {
        Wishlist oldWishlist = repository.findByPseudoAndHash(newWishlist.getPseudo(), newWishlist.getHash()).orElse(null);
        if (oldWishlist == null) return false;

        newWishlist.setId(oldWishlist.getId());
        repository.save(newWishlist);
        return true;
    }

    /**
     * Deletes a review from repository
     * @param pseudo Pseudo of the user reviewing
     * @param hash   Hash of the video being reviewed
     * @return true if the review was deleted, or false if the review couldn't be found
     */
    public boolean deleteOne(String pseudo, String hash) {
        if (!repository.existsByPseudoAndHash(pseudo, hash)) return false;
        repository.deleteByPseudoAndHash(pseudo, hash);
        return true;
    }


    /**
     * Reads all reviews from a user
     * @param pseudo Pseudo of the user
     * @return The list of reviews from this user
     */
    public Iterable<Wishlist> readFromUser(String pseudo) {
        return repository.findByPseudo(pseudo);
    }

    /**
     * Deletes all reviews from a user
     * @param pseudo Pseudo of the user
     */
    public void deleteFromUser(String pseudo) {
        repository.deleteByPseudo(pseudo);
    }


    /**
     * Reads all reviews of a video
     * @param hash Hash of the video
     * @return The list of reviews of this video
     */
    public Iterable<Wishlist> readFromVideo(String hash) {
        return repository.findByHash(hash);
    }

    /**
     * Deletes all reviews of a video
     * @param hash Hash of the video
     */
    public void deleteFromVideo(String hash) {
        repository.deleteByHash(hash);
    }


    /**
     * Finds the 3 best videos by average ranking of users
     * @return the list of videos
     */
    public Iterable<Video> best3Videos() {
        Iterable<Tuple> bests = repository.findBest();
        return StreamSupport.stream(bests.spliterator(), false)
                .limit(3)
                .map(best -> {
                    String hash = (String) best.get("videoHash");
                    return videosProxy.readOne(hash);
                })
                .toList();
    }

}
