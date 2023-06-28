package com.github.chenrui333;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class GraphqlServer extends Jooby {
  private final Logger log = LoggerFactory.getLogger(GraphqlServer.class);
  {
    setServerOptions(new ServerOptions()
        .setPort(8000));

    get("/", ctx -> "Hello World!");
    post("/", ctx -> "Hello World Post!");
    post("/graphql", ctx ->{


      var rawPayload = new String(ctx.body().bytes());
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(rawPayload);
      String query = jsonNode.get("query").asText();
      ExecutionResult er = new GraphqlQueryExecutor().execute(query);
      // turn object into Json, then Json into a String
      return "{ \"data\":\n" +
        "          {\n" +
        "          \"self\": { \"id\": \"202446493\" }\n" +
        "          }\n" +
        "        }";
  });
  }


  public static void main(final String[] args) {
	runApp(args, GraphqlServer::new);
  }
}
