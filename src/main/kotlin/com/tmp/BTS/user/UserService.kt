package com.tmp.BTS.user;

import com.tmp.BTS.exception.BadRequestException
import com.tmp.BTS.exception.ErrorCode
import com.tmp.BTS.user.model.User
import com.tmp.BTS.user.UserRepository
import com.tmp.BTS.util.LogEvent
import com.sun.istack.NotNull
import com.tmp.BTS.security.model.AuthUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.collections.HashMap

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun signUp(@NotNull id : String, @NotNull password : String, @NotNull address: String, @NotNull phone: String, @NotNull isAgree:Boolean): User {

        val user:User = User(id, password, address, phone, isAgree)
        userRepository.save(user)

        return user
    }

    fun signIn(id : String, authUser:AuthUser): User? {
        val user: User = userRepository.findByIdAndPassword(id, authUser.password)

        return user
    }

    fun getUserById(@NotNull id: Long): User {
        val user: User = userRepository.findById(id).get()

        return user
    }

}