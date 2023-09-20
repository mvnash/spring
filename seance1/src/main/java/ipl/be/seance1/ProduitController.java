package ipl.be.seance1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProduitController {

    private static final List<Produit> produits = new ArrayList<>();

    @GetMapping("/produits")
    public Iterable<Produit> readAll(){
        return produits;
    }

    @PostMapping("/produits")
    public ResponseEntity<Produit> createOne(@RequestBody Produit produit) {
        produits.add(produit);
        return new ResponseEntity<>(produit, HttpStatus.CREATED);
    }

    @DeleteMapping("/produits/{id}")
    public ResponseEntity<Produit> deleteOne(@PathVariable int id) {
        Produit produitRemove = null;
        for (int i = 0; i < produits.size();i++){
            Produit produit = produits.get(i);
            if (produit.getId() == id) {
                produitRemove = produit;
                break;
            }
        }
        if (produitRemove == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        produits.remove(produitRemove);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/produits/{id}")
    public ResponseEntity<Produit> updateOne(@PathVariable int id, @RequestBody Produit newProduit) {
        int produitUpdateIndex = -1;
        for (int i = 0; i < produits.size();i++){
            Produit produit = produits.get(i);
            if (produit.getId() == id) {
                produitUpdateIndex = i;
                break;
            }
        }
        if (produitUpdateIndex == -1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        produits.get(produitUpdateIndex).setId(newProduit.getId());
        produits.get(produitUpdateIndex).setName(newProduit.getName());
        produits.get(produitUpdateIndex).setCategory(newProduit.getCategory());
        produits.get(produitUpdateIndex).setPrice(newProduit.getPrice());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
