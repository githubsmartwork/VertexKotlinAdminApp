package com.coats.d3.admin

import com.coats.d3.testutil.getTestConfig
import com.coats.d3.testutil.testAuthorization
import com.coats.d3.testutil.testMasterAuth
import com.coats.d3.util.config.Config
import io.kotlintest.matchers.boolean.shouldBeTrue
import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.shouldBe

import io.vertx.core.Vertx
import io.vertx.ext.web.client.WebClient
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.apache.http.HttpResponse
import org.apache.http.HttpStatus
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.nio.Buffer
import javax.xml.ws.AsyncHandler

@Tag("integrationTest")
@ExtendWith(VertxExtension::class)
class AdminApplicationVerticleIT {

    companion object {
        @BeforeAll
        @JvmStatic
        internal fun setup(vertx: Vertx, context: VertxTestContext) {
            val configJson = getTestConfig()
            val config = Config.Builder(vertx).withJson(configJson).build()
            vertx.deployVerticle(AdminApplicationVerticle(config), context.completing())
        }
    }

    @Test
    fun `Can get status endpoint and receive a valid static html page response`(vertx: Vertx, context: VertxTestContext) {
        val httpClient = WebClient.create(vertx)
        httpClient.get(8080, "localhost", "/").putHeader("Authorization", testAuthorization).send { response ->
            context.verify {
                response.succeeded().shouldBeTrue()
                response.result().statusCode().shouldBe(HttpStatus.SC_OK)
                context.completeNow()
            }
        }
    }

    @Test
    fun `Can get status salesOrg page `(vertx: Vertx, context: VertxTestContext) {
        val httpClient = WebClient.create(vertx)
        httpClient.get(8080, "localhost", "/salesorg").putHeader("Authorization", testAuthorization).send { response ->
            context.verify {
                response.succeeded().shouldBeTrue()
                response.result().statusCode().shouldBe(HttpStatus.SC_OK)
                context.completeNow()
            }
        }
    }

    @Test
    fun `Can get status endpoint and receive a Not valid static  response`(vertx: Vertx, context: VertxTestContext) {
        val httpClient = WebClient.create(vertx)
        httpClient.get(8080, "localhost", "/someapi").putHeader("Authorization", testAuthorization).send { response ->
            context.verify {
                response.result().statusCode().shouldBe(HttpStatus.SC_NOT_FOUND)
                context.completeNow()
            }
        }
    }


    @Test
    fun `Authorization must be provided to use the endpoin page response`(vertx: Vertx, context: VertxTestContext) {
        val httpClient = WebClient.create(vertx)
        httpClient.get(8080, "localhost", "/").send { response ->
            context.verify {
                response.succeeded().shouldBeTrue()
                response.result().statusCode().shouldBe(HttpStatus.SC_UNAUTHORIZED)
                context.completeNow()
            }
        }
    }

    @Test
    fun `Check for unauthenicated SaleOrgData`(vertx: Vertx, context: VertxTestContext) {
        val httpClient = WebClient.create(vertx)
        httpClient.get(8080, "localhost", "/salesorg/IN50").send { res ->
            context.verify {
                res.succeeded().shouldBeTrue()
                res.result().statusCode().shouldBe(HttpStatus.SC_UNAUTHORIZED)
                context.completeNow()
            }
        }
    }

    @Test
    fun `Check for authenicated SaleOrgData`(vertx: Vertx, context: VertxTestContext) {
        val httpClient = WebClient.create(vertx)
        httpClient.get(8080, "localhost", "/salesorg/IN50").putHeader("Authorization", testMasterAuth.trim()).send { res ->
            context.verify {
                res.succeeded().shouldBeTrue()
                res.result().statusCode().shouldBe(HttpStatus.SC_OK)
                res.result().bodyAsString() shouldContain "IN50"
                context.completeNow()
            }
        }
    }

    @Test
    fun `Check for failure SaleOrgData`(vertx: Vertx, context: VertxTestContext) {
        val httpClient = WebClient.create(vertx)
        httpClient.get(8080, "localhost", "/salesorg/1234").putHeader("Authorization", testMasterAuth.trim()).send { res ->
            context.verify {
                res.succeeded().shouldBeTrue()
                res.result().statusCode().shouldBe(HttpStatus.SC_OK)
                res.result().bodyAsString() shouldContain "Data not found"
                context.completeNow()
            }
        }
    }

    // have to fix the timeout section
    @Test
    fun `Check for TimeOut SaleOrgData`(vertx: Vertx, context: VertxTestContext) {
        val httpClient = WebClient.create(vertx)
        httpClient.get(8080, "localhost", "/salesorg/IN50").putHeader("Authorization", testMasterAuth.trim()).send { res ->
            Thread.sleep(3000)
            context.verify {
                res.succeeded().shouldBeTrue()
                res.result().statusCode().shouldBe(HttpStatus.SC_OK)
                res.result().bodyAsString() shouldContain "IN50"
                context.completeNow()
            }
        }
    }

    @Test
    fun `Check for Error -  SaleOrgData`(vertx: Vertx, context: VertxTestContext) {
        val httpClient = WebClient.create(vertx)
        httpClient.get(8080, "localhost", "/salesorg/ABC").putHeader("Authorization", testMasterAuth.trim()).send { res ->
            context.verify {
                res.succeeded().shouldBeTrue()
                res.result().statusCode().shouldBe(HttpStatus.SC_OK)
                res.result().statusMessage() shouldContain "Error"
                context.completeNow()
            }
        }
    }
}

