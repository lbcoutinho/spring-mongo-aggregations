package br.com.lbcoutinho.mongoaggregations.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserPromotionsResponse {

    private String name; // Comes from applicationUser
    private List<Promotion> promotions = new ArrayList<>();

    public void addPromotion(String title, String description, boolean used) {
        promotions.add(new Promotion(title, description, used));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Promotion {

        private String promotionTitle; // Comes from promotion
        private String promotionDescription; // Comes from promotion
        private Boolean used; // Comes from promotionApplicationUser
    }
}
