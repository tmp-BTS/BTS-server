package com.tmp.BTS.exception

import java.lang.Exception

open class BTSException : RuntimeException {
    var code: ErrorCode = ErrorCode.BadRequest
    var logEventCode: String = ""

    constructor(message: String): super(message) {}
    constructor(message: String, code: ErrorCode, logEventCode: String = ""): super(message) {
        this.code = code
        this.logEventCode = logEventCode
    }
    constructor(message: String, code: ErrorCode, logEventCode: String = "", ex: Exception): super(message, ex) {
        this.code = code
        this.logEventCode = logEventCode
    }
}

class InternalException : BTSException {
    constructor(message: String): super(message) {}
    constructor(message: String, code: ErrorCode, logEventCode: String = ""): super(message, code, logEventCode)
}

class BadRequestException : BTSException {
    constructor(message: String): super(message) {}
    constructor(message: String, code: ErrorCode, logEventCode: String = ""): super(message, code, logEventCode)
}

class InvalidParameterException : BTSException {
    constructor(message: String): super(message) {}
    constructor(message: String, code: ErrorCode, logEventCode: String = ""): super(message, code, logEventCode)
}

class NotFoundException : BTSException {
    constructor(message: String): super(message) {}
    constructor(message: String, code: ErrorCode, logEventCode: String = ""): super(message, code, logEventCode)
}

class UnAuthorizeException : BTSException {
    constructor(message: String): super(message) {}
    constructor(message: String, code: ErrorCode, logEventCode: String = ""): super(message, code, logEventCode)
}