package com.tmp.BTS.token

import com.tmp.BTS.user.model.User
import io.jsonwebtoken.Claims
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.HashMap

@Service
class RefreshTokenService : TokenService() {

    private val refreshTokenExpireAt = 15552000 // 6개월

    override fun getExpirationDate(): Long {
        val now = Date()
        val expiredTime :Int = refreshTokenExpireAt

        return (now.time/1000) + expiredTime
    }

    fun generate(user: User): String {
        val additionalInfos = HashMap<String, Any?>()
        additionalInfos.put("userId", user.id)
        additionalInfos.put("tokenType", this.getTokenType(TokenType.REFRESH_TOKEN))
        val jwt :String = encodeJWT(additionalInfos)

        return jwt
    }

    override fun decodeAndGetInfos(JWTToken: String): AuthTokenInfos {
        val body: Claims = this.decodeJWT(JWTToken)

        val userId: Long = (body["userId"] as Int).toLong()
        val tokenType: Int = body["tokenType"] as Int
        val authTokenInfos = AuthTokenInfos(userId, tokenType)

        return authTokenInfos
    }
}