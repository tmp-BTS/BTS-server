package com.tmp.BTS.user

import com.tmp.BTS.user.model.User
import com.tmp.BTS.user.model.UserToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserTokenService {

    @Autowired
    lateinit var userTokenRepository: UserTokenRepository

    fun saveUserToken(user: User, fcmToken: String?, refreshToken: String?) {
        var userToken = userTokenRepository.findByUser(user)
        if (userToken == null ) {
            userToken = UserToken(user, fcmToken, refreshToken)
        } else {
            if (fcmToken != null) userToken.fcmToken = fcmToken
            if (refreshToken != null) userToken.refreshToken = refreshToken
        }

        userTokenRepository.save(userToken)
    }
}