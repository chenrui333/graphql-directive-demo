package com.github.chenrui333;

import io.jooby.Jooby;
import io.jooby.ServerOptions;

public class GraphqlServer extends Jooby {
  {
    setServerOptions(new ServerOptions()
        .setPort(8000));

    get("/", ctx -> "Hello World!");
    post("/", ctx -> "Hello World Post!");
    post("/graphql", ctx -> new GraphqlQueryExecutor().execute(ctx.body().value("query")));
  }


  public static void main(final String[] args) {
	runApp(args, GraphqlServer::new);
  }
}
