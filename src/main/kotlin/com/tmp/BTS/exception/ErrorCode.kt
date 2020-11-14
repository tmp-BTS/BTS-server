package com.tmp.BTS.exception

enum class ErrorCode(val message: String, val status: Int) {
    BadRequest("잠시 후 다시 시도해주세요.", 100),
    InvalidAccessToken("다시 로그인 해주세요.", 101),

    UnknownProvider("알 수 없는 계정 정보입니다.", 110),

    NotAddStore("store 기록에 실패했습니다.", 140),

}
