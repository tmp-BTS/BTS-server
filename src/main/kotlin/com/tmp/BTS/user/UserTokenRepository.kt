package com.tmp.BTS.user

import com.tmp.BTS.user.model.User
import com.tmp.BTS.user.model.UserToken
import org.springframework.data.repository.CrudRepository

interface UserTokenRepository : CrudRepository<UserToken, Long> {
    fun findByUser(user: User): UserToken?
}