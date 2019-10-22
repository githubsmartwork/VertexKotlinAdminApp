package com.coats.d3.admin

import com.coats.d3.masterdata.SalesOrgReply
import com.coats.d3.masterdata.SalesOrgRequest
import com.coats.d3.masterdata.getSalesOrg
import com.coats.d3.util.kotlin.CoroutineVerticle
import com.coats.d3.util.auth.UserInfoHandler
import com.coats.d3.util.auth.createJwtHandler
import com.coats.d3.util.config.Config
import com.coats.d3.util.rpc.getChannel
import com.coats.d3.util.rpc.getMasterDataStub
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.templ.handlebars.HandlebarsTemplateEngine
import io.vertx.kotlin.core.http.listenAwait
import mu.KotlinLogging


class AdminApplicationVerticle(val conf: Config) : CoroutineVerticle() {
    private val log = KotlinLogging.logger { }

    public override suspend fun start() {
        log.debug { "Starting AdminApplicationVerticle" }
        val router = Router.router(vertx)
        router.route().handler(createJwtHandler(conf, setOf(), vertx))
        router.route().handler(createJwtHandler(conf, setOf("access:masterdata"), vertx)).path("/salesorg/:code")
        router.route().handler(UserInfoHandler())
        router.route().handler(BodyHandler.create())
        router.get("/").coroutineHandler(this::handlerRoot)
        router.get("/salesorg/:code").coroutineHandler(this::handleSalesOrgData)
        router.get("/salesorg").coroutineHandler(this::handleSalesOrg)
        vertx.createHttpServer().requestHandler(router).listenAwait(8080)
        log.info { "AdminApplicationVerticle Started in 8080 port" }
    }

    suspend fun handlerRoot(routingContext: RoutingContext) {
        val engine = HandlebarsTemplateEngine.create(vertx)
        val jsonObject= JsonObject()
        try {
            jsonObject.put("IP", getHostAddress(routingContext))
            engine.render(jsonObject, "templates/myTemplate.hbs") { res ->
                log.info { " inside router template " }
                if (res.succeeded()) {
                    //log.info { "succeeded ??? \n ${res.result()}" }
                    val response = routingContext.response()
                    response.putHeader("content-type", "text/html")
                            .setChunked(true)
                            .end(res.result())

                } else {
                    routingContext.fail(500)
                }
            }
        }
        catch (e:Exception){
            log.error { e.message }
        }
    }

    suspend fun handleSalesOrg(routingContext: RoutingContext) {
        val engine = HandlebarsTemplateEngine.create(vertx)
        val jsonObject = JsonObject()
        try {
            engine.render(jsonObject, "templates/salesOrgData.hbs") { res ->
                if (res.succeeded()) {
                    val response = routingContext.response()
                    response.putHeader("content-type", "text/html")
                            .setChunked(true)
                            .end(res.result())

                } else {
                    routingContext.fail(500)
                }
            }
        } catch (e: Exception) {
            log.error { e.message }
        }
    }

    fun getHostAddress(routingContext: RoutingContext): String? {
        return routingContext.request().remoteAddress().host()
    }

    suspend fun handleSalesOrgData(cxt: RoutingContext) {
        log.debug { "Handling Sales org Request" }
        lateinit var salesOrgReply: SalesOrgReply
        val code = cxt.request().getParam("code")
        val resp = cxt.response()
        try {
            log.info { "Getting Sales org data for the code: $code" }
            val channel = getChannel(conf.getString("MASTERDATA_TARGET"))
            val stub = getMasterDataStub(cxt, channel)
            val request = SalesOrgRequest.newBuilder().setCode(code).build()
            salesOrgReply = stub.getSalesOrg(request)
            cxt.response().statusMessage = "success"
            cxt.response().end(salesOrgReply.toString())
            channel.shutdown()
        }catch (e:StatusRuntimeException){
            log.error { "Error in get the data for Sale org : ${code}. Error Message: ${e.message}" }
            cxt.response().statusMessage = "Unable to Retrieve the Data"
            if (e.status.code.equals(Status.NOT_FOUND.code)) {
                cxt.response().statusMessage = "Data not found"
                return cxt.response().end("Data not found")
            } else {
                cxt.response().statusMessage = "Unknown Error"
                return cxt.response().end("Error ${e.message}")
            }
        }
    }
}

