package br.com.lbcoutinho.mongoaggregations.repository;

import br.com.lbcoutinho.mongoaggregations.model.PromotionApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionApplicationUserRepository extends MongoRepository<PromotionApplicationUser, String>,
        CustomPromotionApplicationUserRepository {

    List<PromotionApplicationUser> findByUserDocument(String userDocument);
}
