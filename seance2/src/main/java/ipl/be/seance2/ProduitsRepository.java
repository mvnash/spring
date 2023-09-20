package ipl.be.seance2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitsRepository extends CrudRepository<Produit, String> {

}

