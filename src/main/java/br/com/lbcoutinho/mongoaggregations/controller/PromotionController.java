package br.com.lbcoutinho.mongoaggregations.controller;

import br.com.lbcoutinho.mongoaggregations.service.PromotionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promotion")
public class PromotionController {

    private PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping("/no-aggregation/user/{document}")
    public ResponseEntity getUserPromotions(@PathVariable String document) {
        return ResponseEntity.of(promotionService.getUserPromotionsWithoutAggregation(document));
    }
}
