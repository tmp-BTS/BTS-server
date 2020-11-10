package com.tmp.BTS.store.controller

import com.tmp.BTS.store.service.StoreService
import com.tmp.BTS.store.model.Store
import com.tmp.BTS.store.dto.StoreDto
import javafx.scene.paint.Color.web
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    @PostMapping("/[uid]")
    fun addStore(@RequestBody @Valid storeDto: StoreDto, request: HttpServletRequest): ResponseEntity<HashMap<String, Any>> {
        val store : Store = request.getAttribute("store") as Store

        val result = storeService.addStorebyBeacon(store, storeDto)
        if(!result) throw BadRequestException("fail add store", Errorcode.NotAddStore, LogEvent.StoreControllerProcess.code)

        return ResponseEntity(HttpStatus.CREATED)
    }
}