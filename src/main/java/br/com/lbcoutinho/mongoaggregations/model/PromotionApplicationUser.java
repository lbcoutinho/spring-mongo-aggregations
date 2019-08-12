package br.com.lbcoutinho.mongoaggregations.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "promotionApplicationUser")
@Data
@NoArgsConstructor
public class PromotionApplicationUser {

    @Id
    private String id;

    @Indexed
    private String userDocument;
    private String promotionId;
    private Boolean used = false;

    public PromotionApplicationUser(String userDocument, String promotionId, Boolean used) {
        this.userDocument = userDocument;
        this.promotionId = promotionId;
        this.used = used;
    }
}
