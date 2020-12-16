package com.tmp.BTS.token

import com.tmp.BTS.user.model.User
import io.jsonwebtoken.Claims
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashMap

@Service
class AccessTokenService : TokenService() {

    private val accessTokenExpireAt = 86400000 // 24시간

    override fun getExpirationDate(): Long {
        val now = Date()
        val expiredTime: Int = accessTokenExpireAt

        return (now.time/1000) + expiredTime
    }

    fun generate(user: User): String {
        val additionalInfos = HashMap<String, Any?>()
        additionalInfos.put("userId", user.id)
        additionalInfos.put("tokenType", this.getTokenType(TokenType.ACCESS_TOKEN))
        val jwt :String = encodeJWT(additionalInfos)

        return jwt
    }

    override fun decodeAndGetInfos(JWTToken: String): AuthTokenInfos {
        val body: Claims = this.decodeJWT(JWTToken)

        val userId: Long = (body["userId"] as Int).toLong()
        val tokenType: Int = body["tokenType"] as Int
        val authTokenInfos = AuthTokenInfos(userId,tokenType)

        return authTokenInfos
    }
}