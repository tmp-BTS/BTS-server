package com.tmp.BTS.store.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "history")
data class History(
        @EmbeddedId
        private val id: HistoryKey?,

//        @MapsId("user_id")
//        @JoinColumn(name = "user_id")
//        @JsonBackReference
//        var user: User,

        @MapsId("store_id")
        @JoinColumn(name = "store_id")
        @JsonBackReference
        var store: Store,

        @Column
        var temperature: String,

        @Column
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        var time: LocalDateTime = LocalDateTime.now()
) {
        constructor(store : Store, temperature : String, time : LocalDateTime) : this(HistoryKey(store.id), store, temperature, time)
}