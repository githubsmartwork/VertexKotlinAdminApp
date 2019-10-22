package com.coats.d3.admin

import com.github.jknack.handlebars.Handlebars
import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.matchers.string.shouldMatch
import io.kotlintest.specs.AbstractAnnotationSpec
import io.kotlintest.specs.StringSpec
import io.kotlintest.specs.Test
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.vertx.ext.web.RoutingContext


class MessageServiceTest : StringSpec() {

    private val rc = mockk<RoutingContext>(relaxed = true)
    private val service = mockk<AdminApplicationVerticle>()

    @AbstractAnnotationSpec.AfterAll
    fun clearmock() {
        clearMocks(rc, service)
    }

    init {
        "checkHandlerParsing" {
            val handlebars = Handlebars()
            val template = handlebars.compile("mytemplate")
            val templateString = template.apply("Satheesh")
            templateString.shouldContain("Satheesh")
        }

         "check the getHostAddress root" {
            every { service.getHostAddress(rc) } returns "1.1.1.1"
            // when
            val result = service.getHostAddress(rc)
            // then
            verify { service.getHostAddress(rc) }
            result shouldMatch "[1-9]{1,3}\\.[1-9]{1,3}\\.[1-9]{1,3}\\.[1-9]{1,3}"
        }
    }
}

