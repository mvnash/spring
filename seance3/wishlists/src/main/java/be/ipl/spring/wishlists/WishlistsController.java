package be.ipl.spring.wishlists;

import be.ipl.spring.wishlists.models.Wishlist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/wishlists/user/{pseudo}")
    public Optional<Wishlist> readFromUser(@PathVariable String pseudo) {
        return service.readFromUser(pseudo);
    }

    @DeleteMapping("/wishlists/user/{pseudo}")
    public void deleteFromUser(@PathVariable String pseudo) {
        service.deleteFromUser(pseudo);
    }

    @DeleteMapping("/wishlists/user/{pseudo}")
    public void deleteFromProduct(@PathVariable int product) {
        service.deleteFromProduct(product);
    }


}
