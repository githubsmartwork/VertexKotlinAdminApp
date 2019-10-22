package com.coats.d3.util.rpc

import com.coats.d3.masterdata.MasterDataServiceGrpc
import io.grpc.ManagedChannel
import io.kotlintest.matchers.collections.shouldContainAll
import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.specs.StringSpec
import io.kotlintest.specs.Test
import io.mockk.every
import io.mockk.mockk
import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.jwt.impl.JWTUser
import io.vertx.ext.web.RoutingContext
import org.jetbrains.annotations.NotNull
import org.junit.jupiter.api.Tag

@Test
class rpcchannelTest : StringSpec() {
    init {

        "Check for Channel creation"{
            val chnl =  getChannel("Local:9090")
            assert(chnl is ManagedChannel)
        }

        "check for stub"{
            val ctx = mockk<RoutingContext>(relaxed = true)
            val stub = getMasterDataStub(ctx, getChannel("test:9090"))
            assert(stub is MasterDataServiceGrpc.MasterDataServiceStub)
        }
    }
}