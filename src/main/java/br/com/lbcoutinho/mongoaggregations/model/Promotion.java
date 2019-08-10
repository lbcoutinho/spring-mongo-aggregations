package br.com.lbcoutinho.mongoaggregations.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "promotion")
@Data
@NoArgsConstructor
public class Promotion {

    @Id
    private String id;

    private String title;
    private String description;
    private Date startDate;
}
