package be.ipl.spring.wishlists;

import be.ipl.spring.wishlists.models.Video;
import be.ipl.spring.wishlists.models.Wishlist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WishlistsController {

    private final WishlistsService service;

    public WishlistsController(WishlistsService service) {
        this.service = service;
    }

    @PostMapping("/wishlists")
    public ResponseEntity<Void> createOne(@RequestBody Wishlist wishlist) {
        boolean created = service.createOne(wishlist);
        if (!created) return new ResponseEntity<>(HttpStatus.CONFLICT);
        else return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/wishlists")
    public ResponseEntity<Wishlist> readOne(@RequestParam String pseudo, @RequestParam String hash) {
        Wishlist wishlist = service.readOne(pseudo, hash);
        if (wishlist == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }

    @PutMapping("/wishlists")
    public ResponseEntity<Void> updateOne(@RequestBody Wishlist wishlist) {
        boolean updated = service.updateOne(wishlist);
        if (!updated) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/wishlists")
    public ResponseEntity<Void>  deleteOne(@RequestParam String pseudo, @RequestParam String hash) {
        boolean deleted = service.deleteOne(pseudo, hash);
        if (!deleted) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/wishlists/user/{pseudo}")
    public Iterable<Wishlist> readFromUser(@PathVariable String pseudo) {
        return service.readFromUser(pseudo);
    }

    @DeleteMapping("/wishlists/user/{pseudo}")
    public void deleteFromUser(@PathVariable String pseudo) {
        service.deleteFromUser(pseudo);
    }


    @GetMapping("/wishlists/video/{hash}")
    public Iterable<Wishlist> readFromVideo(@PathVariable String hash) {
        return service.readFromVideo(hash);
    }

    @DeleteMapping("/wishlists/video/{hash}")
    public void deleteFromVideo(@PathVariable String hash) {
        service.deleteFromVideo(hash);
    }

}
