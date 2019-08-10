package br.com.lbcoutinho.mongoaggregations.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "applicationUser")
@Data
@NoArgsConstructor
public class ApplicationUser {

    @Id
    private String id;

    private String name;
    private String email;
    private String document;

}
