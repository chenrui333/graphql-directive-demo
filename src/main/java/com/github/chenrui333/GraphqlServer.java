package com.github.chenrui333;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class GraphqlServer extends Jooby {
  private final Logger log = LoggerFactory.getLogger(GraphqlServer.class);
  {
    setServerOptions(new ServerOptions()
        .setPort(8000));

    get("/", ctx -> "Hello World!");
    post("/", ctx -> "Hello World Post!");
    post("/graphql", ctx ->{
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(ctx.body().value());
      String query = jsonNode.get("query").asText();
      ExecutionResult executionResult = new GraphqlQueryExecutor().execute(query);
      Map<String, Object> resultObject = executionResult.toSpecification();
      return  objectMapper.writeValueAsString(resultObject);
  });
  }


  public static void main(final String[] args) {
	runApp(args, GraphqlServer::new);
  }
}
