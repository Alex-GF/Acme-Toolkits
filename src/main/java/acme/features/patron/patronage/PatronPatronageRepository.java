package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.framework.repositories.AbstractRepository;

public class PatronPatronageRepository extends AbstractRepository {

    @Query("SELECT p FROM Patronage p WHERE p.patron.userAccount.id = :patronId")
    Collection<Patronage> findPatronagesByPatronId(int patronId);

}
