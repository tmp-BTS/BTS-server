package com.tmp.BTS.util

enum class LogEvent(val code: String) {
    // Global Exception
    MissingServletRequestParameterException("GBE001"),
    HttpMessageNotReadableException("GBE002"),
    GlobalException("GBE003"),

    //StoreService Process
    StoreServiceProcess("SSP001"),

    // StoreController Process
    StoreControllerProcess("SCP001"),

    AuthServiceProcessError("ASPE01"),

    TokenServiceProcessError("TSPE01"),
}