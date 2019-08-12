package br.com.lbcoutinho.mongoaggregations.repository;

import br.com.lbcoutinho.mongoaggregations.aggregation.JsonOperation;
import br.com.lbcoutinho.mongoaggregations.model.ApplicationUser;
import br.com.lbcoutinho.mongoaggregations.service.dto.UserPromotionsResponse;
import br.com.lbcoutinho.mongoaggregations.util.AggregationUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

public class CustomPromotionApplicationUserRepositoryImpl implements CustomPromotionApplicationUserRepository {

    private MongoTemplate mongoTemplate;
    private AggregationUtils aggregationUtils;

    public CustomPromotionApplicationUserRepositoryImpl(MongoTemplate mongoTemplate, AggregationUtils aggregationUtils) {
        this.mongoTemplate = mongoTemplate;
        this.aggregationUtils = aggregationUtils;
    }

    public UserPromotionsResponse getUserPromotions(String userDocument) {
        MatchOperation matchUsersByDocument = match(Criteria.where("document").is(userDocument));
        JsonOperation lookupUsersPromotions = aggregationUtils.getOperation("user.promotions.lookup.get.promotions");

        // Alternative implementation with inline operation
//      JsonOperation lookupUsersPromotions = new JsonOperation(
//                "{ $lookup: {" +
//                "from:'promotionApplicationUser'," +
//                "let:{user_document:'$document'}," +
//                "pipeline:[" +
//                "{$match:{$expr:{$eq:['$userDocument','$$user_document']}}}," +
//                "{$lookup:{from:'promotion',localField:'promotionId',foreignField:'_id',as:'promotion'}}," +
//                "{$unwind:'$promotion'}," +
//                "{$project:{_id:0,used:1,promotionTitle:'$promotion.title',promotionDescription:'$promotion.description'}}" +
//                "]," +
//                "as:'promotions'}}");

        Aggregation aggregation = Aggregation.newAggregation(matchUsersByDocument, lookupUsersPromotions);

        return mongoTemplate.aggregate(aggregation, ApplicationUser.class, UserPromotionsResponse.class).getUniqueMappedResult();
    }


}
