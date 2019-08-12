package br.com.lbcoutinho.mongoaggregations.util;


import br.com.lbcoutinho.mongoaggregations.aggregation.JsonOperation;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class AggregationUtils {

    private final MessageSource messageSource;

    public AggregationUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public JsonOperation getOperation(String key, String... arguments) {
        String operation = messageSource.getMessage(key, arguments, Locale.getDefault());
        System.out.println("operation = " + operation);
        return new JsonOperation(operation);
    }

}
