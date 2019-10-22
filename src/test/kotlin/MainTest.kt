package com.coats.d3


import io.kotlintest.specs.StringSpec


class MainTest : StringSpec() {

    init {
        main(arrayOf("test"))
        assert(true)
    }
}