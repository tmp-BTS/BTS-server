package com.tmp.BTS.store.controller

import com.tmp.BTS.exception.BadRequestException
import com.tmp.BTS.exception.ErrorCode
import com.tmp.BTS.store.dto.HistoryDto
import com.tmp.BTS.store.dto.HistoryListDto
import com.tmp.BTS.store.model.History
import com.tmp.BTS.store.service.StoreService
import com.tmp.BTS.util.LogEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/store")
class StoreController {

    @Autowired
    lateinit var storeService: StoreService

    //addStore API
    @ResponseBody
    @PostMapping("/add")
    fun addStore(@RequestBody @Valid historyDto: HistoryDto, request: HttpServletRequest): ResponseEntity<HashMap<String, Any>> {
        val history : History = request.getAttribute("history") as History

        val result = storeService.addStoreByBeacon(historyDto)
        if(!result) throw BadRequestException("fail add store", ErrorCode.NotAddStore, LogEvent.StoreControllerProcess.code)

        return ResponseEntity(HttpStatus.CREATED)
    }

    @ResponseBody
    @GetMapping("/history")
    fun storeHistory(request: HttpServletRequest):ResponseEntity<List<HistoryListDto>> {
        val response = storeService.fetchHistoryList()

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(response)
    }

}