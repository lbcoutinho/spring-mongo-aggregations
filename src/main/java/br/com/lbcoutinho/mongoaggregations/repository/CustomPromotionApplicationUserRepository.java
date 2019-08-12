package br.com.lbcoutinho.mongoaggregations.repository;

import br.com.lbcoutinho.mongoaggregations.service.dto.UserPromotionsResponse;

public interface CustomPromotionApplicationUserRepository {

    UserPromotionsResponse getUserPromotions(String userDocument);
}
