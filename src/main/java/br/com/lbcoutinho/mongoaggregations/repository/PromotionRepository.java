package br.com.lbcoutinho.mongoaggregations.repository;

import br.com.lbcoutinho.mongoaggregations.model.Promotion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public interface PromotionRepository extends MongoRepository<Promotion, String> {

    Stream<Promotion> findByIdIn(List<String> ids);

    Optional<Promotion> findByTitle(String title);
}
