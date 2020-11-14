package com.tmp.BTS.store.controller

import com.tmp.BTS.exception.BadRequestException
import com.tmp.BTS.exception.ErrorCode
import com.tmp.BTS.store.service.StoreService
import com.tmp.BTS.util.LogEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
//import javax.validation.Valid

@RestController
@RequestMapping("/store")
class StoreController {

    @Autowired
    lateinit var storeService: StoreService

    //addStore API
    @ResponseBody
    @PostMapping("/add")
    fun addStore(@RequestParam(value="uuid", required = true) uuid:String,
                 @RequestParam(value = "temperature", required = true)temperature:String,
                 request: HttpServletRequest): ResponseEntity<HashMap<String, Any>> {
        //val history : History = request.getAttribute("history") as History

        val result = storeService.addStoreByBeacon(uuid, temperature)
        if(!result) throw BadRequestException("fail add store by beacon", ErrorCode.NotAddStore, LogEvent.StoreControllerProcess.code)

        return ResponseEntity(HttpStatus.CREATED)
    }
/*
    @ResponseBody
    @GetMapping("/history")
    fun storeHistory(request: HttpServletRequest):ResponseEntity<List<HistoryListDto>> {
        val response = storeService.fetchHistoryList()

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(response)
    }
*/
    @ResponseBody
    @PostMapping("/place")
    fun addPlace(@RequestParam(value="title", required = true) title : String,
                 @RequestParam(value="location", required = true) location : String,
                 request:HttpServletRequest):ResponseEntity<HashMap<String, Any>> {

    val result = storeService.addPlaceByUser(title, location)
    if(!result) throw BadRequestException("fail add place by user", ErrorCode.NotAddPlace, LogEvent.StoreControllerProcess.code)

    return ResponseEntity(HttpStatus.CREATED)
    }
}