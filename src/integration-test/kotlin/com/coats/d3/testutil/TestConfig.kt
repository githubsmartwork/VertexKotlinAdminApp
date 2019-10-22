package com.coats.d3.testutil

import io.vertx.core.json.JsonObject

const val testToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IlJFVkVNakl4TlRFNE1UQXh" +
        "SRGMwTnpFM09UQTJRa016TURoRk9FWkNNVGd4TXpGRE5Ua3hNQSJ9.eyJpc3MiOiJodHRwczovL2NvYXRzLXRlc3QuZXUu" +
        "YXV0aDAuY29tLyIsInN1YiI6ImF1dGgwfDVhOTZhNDQzNDU3ZWNiNjc5YzVlNTkwZCIsImF1ZCI6WyJodHRwczovL2Rldi" +
        "5jb2F0c2QzLmNvbSIsImh0dHBzOi8vY29hdHMtdGVzdC5ldS5hdXRoMC5jb20vdXNlcmluZm8iXSwiaWF0IjoxNTY5MzMz" +
        "ODM1LCJleHAiOjE1Njk0MjAyMzUsImF6cCI6IldwNDMySTBZNVRtR3dlWXRuNHFvRk5QZU9FWnc2UmxOIiwic2NvcGUiOi" +
        "JvcGVuaWQgcHJvZmlsZSBlbWFpbCJ9.bE7CJY1jMlfPA2BGBD4L30hDdTKW41HBWtO6CSpEre7VdDua6RCYbUShCsnx3Tg" +
        "qxQEewdrcrKM4WGgv8P_2C6Zah9u_K4BorY2Wdg6bvnaDLpgQqPyrWhdfrl8Q85hGs3IbO-mhj_d4jTCQgNDF42H8boy0N" +
        "mThpXMYU3dLkMrN6ER9kz65CdvXrK4gq42Hfg_OBfFT0UArmyWrv--7vynz2A-ii0rgP9rWy1zkdc_C3Cjy-PZEwDdvxPj" +
        "JTvKJSqmBTgJhgkXINnumGL1ZouWUTtHpQqLBfWeHBBMY7mJLcv2NHFs1g90t3MqOEt4cAS2Wy6WE2jz5YENv-Og6ug"

const val testAuthorization = "Bearer $testToken"

const val testMastertoken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6IlJFVkVNakl4TlRFNE1UQXhSRGMw" +
        "TnpFM09UQTJRa016TURoRk9FWkNNVGd4TXpGRE5Ua3hNQSJ9.eyJpc3MiOiJodHRwczovL2NvYXRzLXRlc3QuZXUuYXV" +
        "0aDAuY29tLyIsInN1YiI6ImF1dGgwfDVkODlmNmM1YTFiMDIwMGUwZWI4MWU2ZiIsImF1ZCI6Imh0dHBzOi8vZGV2LmN" +
        "vYXRzZDMuY29tIiwiaWF0IjoxNTcwNDU5MTExLCJleHAiOjE1NzA1NDU1MTEsImF6cCI6IldwNDMySTBZNVRtR3dlWXR" +
        "uNHFvRk5QZU9FWnc2UmxOIiwiZ3R5IjoicGFzc3dvcmQiLCJwZXJtaXNzaW9ucyI6WyJhY2Nlc3M6YWNjb3VudG1nbXQ" +
        "iLCJhY2Nlc3M6bWFzdGVyZGF0YSJdfQ.JrB4WLKuXevxUnOb0G8wrEy3_jb98KUZMtpYTKmxny1PUGoDLAAXEo5PXsxw" +
        "WHFescOiMyhY6xGg_SrouVVQ-2z8jX6aWObPBPA4iVRoGJGKyUHfY6SoDdduuTUcc8KG4NHyhyY8NXW9XVMA7BVWo5sa" +
        "RWl5qGiiVLQP7reGyfztYaQrEL0oFd-Fc9a29W61OtIClq-bX9LONgYD7wot9No_k5Nyuqg9OGEaRVUfCDUoYsFuqK59" +
        "f-dv-7jLjhY2KgcFceRNKw1Wf4Jakh7CPhbBN7kf7Yy2WYt-p9eJ28BxwjBx2ZadBNETUMz1jON1_XCgSzfg_gUIua9sfOy_MQ"
const val testMasterAuth = "Bearer $testMastertoken"

fun getTestConfig(): JsonObject {
    val cert = "-----BEGIN CERTIFICATE-----\n" +
            "MIIDCTCCAfGgAwIBAgIJA+iEZMI/8JK5MA0GCSqGSIb3DQEBCwUAMCIxIDAeBgNV\n" +
            "BAMTF2NvYXRzLXRlc3QuZXUuYXV0aDAuY29tMB4XDTE3MTIxNDA3Mjc0NVoXDTMx\n" +
            "MDgyMzA3Mjc0NVowIjEgMB4GA1UEAxMXY29hdHMtdGVzdC5ldS5hdXRoMC5jb20w\n" +
            "ggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC11HcoeezYxaJCTYdKx1Pf\n" +
            "kX6tTxivtwfRHjBq0kjfesDupxohLkDAo7npU5G1Q4xnx0HdIItloPNZsNpyL1WZ\n" +
            "JWFxsxRokBM1a3IdaYIbJE9mmZoqoVkRUjzpOW7mMMG5ycJR9LnzVqBs796yueKl\n" +
            "ZC16eBTuhL4f6c/lUMo8zzzjaZdEjKqBl+KPJ4zCJhHoP+yxUkuAwx1m6/kEoBXK\n" +
            "v5wKY9+xiaVVFGR9cJ6L9HRcfQsx70p2HshdUaDZfyZ9FN02g8M0cZmvzlMjFVMe\n" +
            "PcdrMA0VvagZTPCeInMzfWirF+KfekdTclZWHpPkeXRLuTp7t5/tEySD8d6NSq4F\n" +
            "AgMBAAGjQjBAMA8GA1UdEwEB/wQFMAMBAf8wHQYDVR0OBBYEFDhHbOX7oEbpNkH7\n" +
            "5C3xe4WRT0jEMA4GA1UdDwEB/wQEAwIChDANBgkqhkiG9w0BAQsFAAOCAQEAezTF\n" +
            "fN0vse1gdygvIOjFZgh2Nq9QX/CZRCzDZE8UAuPMWu59YDjb0+I7bwefCNDRRxP/\n" +
            "cViG/KRJKPpmkHF7FFHokPfdPMJIO3U0Ii0mzFEwicUFj4tzk5kBnFvFfjDQuoKL\n" +
            "B3D05GDBqDfQd1WAmEhBvEsSMoelVGof9XIis3Xa6XDrSV3GYKZ7UnjWWuzbs9ok\n" +
            "+YG2mMPwqxysPXLONkAfp7oUbHnHc8VR1QRbx+I7PY5HfzggURd3y+6O1qwXGnPY\n" +
            "bjfzoPDOBNhsA8b5nRnty7i7rjnyNxDvRG7ive/WPx47QYEeI00J/FnDF+KGHcmO\n" +
            "UhnD8mHn6tNfsVpFTQ==\n" +
            "-----END CERTIFICATE-----"

    return JsonObject()
            .put("AUTH0_CERT_PEM", cert)
            .put("AUTH0_ISSUER", "https://coats-test.eu.auth0.com/")
            .put("AUTH0_AUDIENCE", "https://dev.coatsd3.com")
            .put("AUTH0_IGNORE_EXPIRATION", "true")
            .put("MASTERDATA_TARGET", "localhost:8123")
}