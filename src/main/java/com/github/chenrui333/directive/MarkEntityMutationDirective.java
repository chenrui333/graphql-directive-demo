package com.github.chenrui333.directive;

import graphql.schema.*;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class MarkEntityMutationDirective implements SchemaDirectiveWiring {

  private final Logger log = LoggerFactory.getLogger(MarkEntityMutationDirective.class);

  @Override
  public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
    GraphQLFieldDefinition field = environment.getElement();
    log.info("field: {}", field);
    DataFetcher<?> originalFetcher = environment.getCodeRegistry().getDataFetcher(
      environment.getFieldsContainer(), field);

    DataFetcher<?> newFetcher = DataFetcherFactories.wrapDataFetcher(originalFetcher, ((dataFetchingEnvironment, value) -> {
      // Assuming your mutation returns a standard error object you can access.
      // You should adapt this according to your response structure.
      if (value instanceof Map && ((Map) value).get("error") == null) {
        // Assume that your Rating object and input have a field "title".
        Map resultMap = (Map) value;
        String title = (String) resultMap.get("title");
        log.info("Marking entity with title {} as mutated", title);
      }

      return value;
    }));

    return field.transform(builder -> builder.dataFetcher(newFetcher));
  }
}
