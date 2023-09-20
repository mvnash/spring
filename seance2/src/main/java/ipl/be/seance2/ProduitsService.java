package ipl.be.seance2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class ProduitsService {

    private ProduitsRepository pr;

    public ProduitsService(ProduitsRepository pr) {
        this.pr = pr;
    }


    public Iterable<Produit> readAll() {
        return pr.findAll();
    }

    public Produit createOne(Produit produit) {
        return pr.save(produit);
    }

    public boolean deleteOne(int id) {
        if (pr.existsById(String.valueOf(id))) {
            pr.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }

    public boolean updateOne(Produit newProduit) {
        if (!pr.existsById(String.valueOf(newProduit.getId()))) return false;

        pr.save(newProduit);
        return true;
    }
}
