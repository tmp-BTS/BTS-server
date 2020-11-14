package com.tmp.BTS.store.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class HistoryListDto(
        @JsonProperty("title") val title:String,
        @JsonProperty("location")val location:String,
        @JsonProperty("temperature")val temperature:String,
        @JsonProperty("time")val time:String) {
}