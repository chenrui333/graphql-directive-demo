package com.github.chenrui333;

import graphql.ExecutionInput;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphqlServer extends Jooby {
  private final Logger log = LoggerFactory.getLogger(GraphqlServer.class);
  {
    setServerOptions(new ServerOptions()
        .setPort(8000));

    get("/", ctx -> "Hello World!");
    post("/", ctx -> "Hello World Post!");
    post("/graphql", ctx ->{

      var query = ctx.body().value("query");
      log.info("body: {}", ctx.body().value());
      log.info("query: {}", query);
//      String value = ctx.body().value();
//      ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(value).build();
      String value = "query ken_test { movies(titleFilter: \"test\") { title } }";
      return new GraphqlQueryExecutor().execute(value).getData();
  });
  }


  public static void main(final String[] args) {
	runApp(args, GraphqlServer::new);
  }
}
