package com.github.chenrui333;

import io.jooby.Jooby;

public class GraphqlServer extends Jooby {
  {
	get("/", ctx -> "Hello World!");
  }

  public static void main(final String[] args) {
	runApp(args, GraphqlServer::new);
  }
}
