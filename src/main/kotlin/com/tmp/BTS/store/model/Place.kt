package com.tmp.BTS.store.model


import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import com.tmp.BTS.user.model.User
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name="place")
data class Place (

        @MapsId("user_id")
        @JoinColumn(name = "user_id")
        @JsonBackReference
        var user: User,

        @Column
        var title:String,

        @Column
        var location:String,

        @Column
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        var time: LocalDateTime = LocalDateTime.now()

){

}