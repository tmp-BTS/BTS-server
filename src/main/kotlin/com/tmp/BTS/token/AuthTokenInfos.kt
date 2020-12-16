package com.tmp.BTS.token

data class AuthTokenInfos (
        var userId: Long,
        var tokenType: Int
) {
    constructor() : this(0,0)
}