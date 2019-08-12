package br.com.lbcoutinho.mongoaggregations.aggregation;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

public class JsonOperation implements AggregationOperation {

    private String jsonOperation;

    public JsonOperation(String jsonOperation) {
        this.jsonOperation = jsonOperation;
    }

    @Override
    public Document toDocument(AggregationOperationContext context) {
        return context.getMappedObject(Document.parse(jsonOperation));
    }
}