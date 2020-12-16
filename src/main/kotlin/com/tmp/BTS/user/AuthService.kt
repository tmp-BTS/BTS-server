package com.tmp.BTS.user

import com.tmp.BTS.exception.BadRequestException
import com.tmp.BTS.exception.ErrorCode
import com.tmp.BTS.token.AccessTokenService
import com.tmp.BTS.token.RefreshTokenService
import com.tmp.BTS.user.model.User
import com.tmp.BTS.util.LogEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthService {
    private val log = org.slf4j.LoggerFactory.getLogger(AuthService::class.java)

    @Autowired
    lateinit var accessTokenService: AccessTokenService
    @Autowired
    lateinit var refreshTokenService: RefreshTokenService

    fun getAuthTokens(user: User?): HashMap<String, Any> {
        if ( user == null ) {
            throw BadRequestException("user is null", ErrorCode.BadRequest, LogEvent.AuthServiceProcessError.code)
        }

        val accessToken: String = accessTokenService.generate(user)
        val refreshToken: String = refreshTokenService.generate(user)

        val tokenStore = HashMap<String, Any>()
        tokenStore.put("accessToken", accessToken)
        tokenStore.put("refreshToken", refreshToken)

        return tokenStore
    }

}