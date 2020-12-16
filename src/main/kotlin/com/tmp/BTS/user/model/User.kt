package com.tmp.BTS.user.model

import com.tmp.BTS.security.model.AuthUser;
import org.springframework.data.annotation.Id
import javax.persistence.*;

@Entity
@Table(name ="users")
data class User (

    @Id
    @JoinColumn(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId : Int,

    @Column
    val id: String,

    @Column
    val password: String,

    @Column
    val address: String,

    @Column
    val phone: String,

    @Column
    val isAgree : Boolean

){
    fun toAuthUser(): AuthUser {
        return AuthUser(id, password);
    }

    fun  NullUser(){ User(0, "--", "--", "--","--", false);}
}