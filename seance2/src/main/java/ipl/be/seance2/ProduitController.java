package ipl.be.seance2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProduitController {

    private static final List<Produit> produits = new ArrayList<>();
    private final ProduitsService service;

    public ProduitController(ProduitsService service) {
        this.service = service;
    }

    @GetMapping("/produits")
    public Iterable<Produit> readAll(){
        return produits;
    }

    @PostMapping("/produits")
    public ResponseEntity<Produit> createOne(@RequestBody Produit produit) {
        Produit created = service.createOne(produit);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/produits/{id}")
    public ResponseEntity<Produit> deleteOne(@PathVariable int id) {
        return service.deleteOne(id) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/produits/{id}")
    public ResponseEntity<Produit> updateOne(@PathVariable int id, @RequestBody Produit newProduit) {
        return service.updateOne(newProduit) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
