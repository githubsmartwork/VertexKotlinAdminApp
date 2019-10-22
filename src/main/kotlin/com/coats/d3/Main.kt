package com.coats.d3


import com.coats.d3.admin.AdminApplicationVerticle
import com.coats.d3.util.config.Config
import io.vertx.core.Vertx

fun main(args: Array<String>) {
    val vertx = Vertx.vertx()
    val baseConfig = Config.Builder(vertx)
            .withConfigMap("auth0-config")
            .withConfigMap("grpc")
    vertx.deployVerticle(AdminApplicationVerticle(baseConfig.build()))
}
