package br.com.lbcoutinho.mongoaggregations.repository;

import br.com.lbcoutinho.mongoaggregations.model.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String> {

    Optional<ApplicationUser> findByDocument(String document);
}
