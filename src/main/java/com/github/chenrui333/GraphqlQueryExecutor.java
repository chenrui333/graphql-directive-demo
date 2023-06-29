package com.github.chenrui333;

import com.github.chenrui333.directive.MarkEntityMutationDirective;
import com.github.chenrui333.model.Movie;
import com.github.chenrui333.model.Rating;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

public class GraphqlQueryExecutor {

  private final GraphQL graphQL;

  public GraphqlQueryExecutor() {
    // Get the class loader
    ClassLoader classLoader = getClass().getClassLoader();

    // Load the schema file from the classpath
    File schemaFile = new File(classLoader.getResource("schema/schema.graphql").getFile());

    TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
    RuntimeWiring runtimeWiring = buildRuntimeWiring();
    GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring);

    this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
  }

  private RuntimeWiring buildRuntimeWiring() {
    // Add runtime wiring configurations here
    return RuntimeWiring.newRuntimeWiring()
      .directiveWiring(new MarkEntityMutationDirective())
      .type(newTypeWiring("Query")
        .dataFetcher("movies", environment -> {
          String titleFilter = environment.getArgument("titleFilter");
          System.out.print(titleFilter);
          ArrayList<Movie> movies = new ArrayList<>();
          movies.add(new Movie("The Matrix", 1999));
          movies.add(new Movie("The Matrix Reloaded", 2003));
          // Replace this with actual logic to retrieve movies
          return movies;
        }))
      .type(newTypeWiring("Mutation")
        .dataFetcher("addRating", environment -> {
          Map<String, Object> ratingInput = environment.getArgument("rating");
          String title = (String) ratingInput.get("title");
          Integer stars = (Integer) ratingInput.get("stars");
          Integer releaseYear = (Integer) ratingInput.get("releaseYear");
          // Replace this with actual logic to add rating
          return new Rating(stars);
        }))
      .build();
  }
  public ExecutionResult execute(String query) {
     ExecutionInput executionInput = ExecutionInput.newExecutionInput()
       .query(query)
       .build();
    return this.graphQL.execute(executionInput);
  }
}