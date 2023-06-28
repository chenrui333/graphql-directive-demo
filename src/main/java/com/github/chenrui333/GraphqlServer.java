package com.github.chenrui333;

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
      return new GraphqlQueryExecutor().execute(query);
      }
    );
  }


  public static void main(final String[] args) {
	runApp(args, GraphqlServer::new);
  }
}
