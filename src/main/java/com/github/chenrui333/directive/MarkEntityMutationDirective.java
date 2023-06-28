package com.github.chenrui333.directive;

import graphql.schema.GraphQLObjectType;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarkEntityMutationDirective implements SchemaDirectiveWiring {

  private final Logger log = LoggerFactory.getLogger(MarkEntityMutationDirective.class);

  /** when declaired on an object type */
  @Override
  public GraphQLObjectType onObject(SchemaDirectiveWiringEnvironment<GraphQLObjectType> env) {
    var parent = env.getElement();
    parent.getFieldDefinitions().stream()
      .forEach(
        field -> {
          log.info("object intercepted {}", field);
        });
    return parent;
  }
}
