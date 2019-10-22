package com.coats.d3.util.rpc


import com.coats.d3.masterdata.MasterDataServiceGrpc
import com.coats.d3.masterdata.MasterDataServiceGrpc.MasterDataServiceStub
import com.coats.d3.util.auth.JwtCallCredentials
import io.grpc.ManagedChannel
import io.vertx.ext.web.RoutingContext
import io.vertx.grpc.VertxChannelBuilder
import java.util.concurrent.TimeUnit


fun getChannel(target: String): ManagedChannel = VertxChannelBuilder.forTarget(target)
        .idleTimeout(2, TimeUnit.SECONDS)
        .usePlaintext()
        .build()

fun getMasterDataStub(cxt: RoutingContext, channel: ManagedChannel): MasterDataServiceStub = MasterDataServiceGrpc.newStub(channel)
        .withCallCredentials(JwtCallCredentials(jwtToken = cxt.request().headers().get("Authorization").replace("Bearer", "").trim()))   //TODO() Very bad approach to get Token. Have to use Metadata
