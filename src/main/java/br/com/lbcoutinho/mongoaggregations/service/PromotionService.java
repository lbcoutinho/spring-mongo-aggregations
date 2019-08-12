package br.com.lbcoutinho.mongoaggregations.service;

import br.com.lbcoutinho.mongoaggregations.model.ApplicationUser;
import br.com.lbcoutinho.mongoaggregations.model.Promotion;
import br.com.lbcoutinho.mongoaggregations.model.PromotionApplicationUser;
import br.com.lbcoutinho.mongoaggregations.repository.ApplicationUserRepository;
import br.com.lbcoutinho.mongoaggregations.repository.PromotionApplicationUserRepository;
import br.com.lbcoutinho.mongoaggregations.repository.PromotionRepository;
import br.com.lbcoutinho.mongoaggregations.service.dto.UserPromotionsResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PromotionService {

    private PromotionApplicationUserRepository promotionApplicationUserRepository;
    private PromotionRepository promotionRepository;
    private ApplicationUserRepository applicationUserRepository;

    public PromotionService(PromotionApplicationUserRepository promotionApplicationUserRepository, PromotionRepository promotionRepository,
                            ApplicationUserRepository applicationUserRepository) {
        this.promotionApplicationUserRepository = promotionApplicationUserRepository;
        this.promotionRepository = promotionRepository;
        this.applicationUserRepository = applicationUserRepository;
    }

    public Optional<UserPromotionsResponse> getUserPromotionsWithoutAggregation(String userDocument) {
        // Find application user by document
        Optional<ApplicationUser> applicationUser = applicationUserRepository.findByDocument(userDocument);
        if (!applicationUser.isPresent()) {
            return Optional.empty();
        }

        // Find promotions for given user
        List<PromotionApplicationUser> promosForUser = promotionApplicationUserRepository.findByUserDocument(userDocument);

        // Find promotions by id
        List<String> promoIds = promosForUser.stream().map(PromotionApplicationUser::getPromotionId).collect(Collectors.toList());
        Map<String, Promotion> promotionByIdMap = promotionRepository.findByIdIn(promoIds)
                .collect(Collectors.toMap(Promotion::getId, p -> p));

        // Create response list with DTOs
        UserPromotionsResponse response = new UserPromotionsResponse();
        response.setName(applicationUser.get().getName());
        promosForUser.forEach(promoForUser -> {
            Promotion promotion = promotionByIdMap.get(promoForUser.getPromotionId());
            response.addPromotion(promotion.getTitle(), promotion.getDescription(), promoForUser.getUsed());
        });

        return Optional.of(response);
    }

    public Optional<UserPromotionsResponse> getUserPromotions(String userDocument) {
        return Optional.ofNullable(promotionApplicationUserRepository.getUserPromotions(userDocument));
    }
}
