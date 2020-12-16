package com.tmp.BTS.user

import com.tmp.BTS.security.model.AuthUser
import com.tmp.BTS.user.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity


@Controller("UserController")
@RequestMapping("/users")
public class UserController {

    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var passwordEncoder: PasswordEncoder
    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var authService: AuthService
    @Autowired
    lateinit var userTokenService:UserTokenService


    @ResponseBody
    @PostMapping("/sign_up")
    fun signUp(@RequestParam(value="id", required = true) id : String,
               @RequestParam(value="password", required=true)password : String,
               @RequestParam(value="address", required = true) address : String,
               @RequestParam(value="phone", required = true) phone : String,
               @RequestParam(value="isAgree", required = true) isAgree : Boolean): ResponseEntity.BodyBuilder {

        val authUser:AuthUser = AuthUser(id, password)
        val user:User = userService.signUp(authUser.getId(), passwordEncoder.encode(authUser.getPassword()),address, phone, isAgree)

        return ResponseEntity.ok()
    }

    @ResponseBody
    @PostMapping("/sign_in")
    fun signIn(@RequestBody authUser:AuthUser): ResponseEntity<HashMap<String, Any>> {
        val id: String =  authUser.getId()

        val user: User? = userService.signIn(id, authUser)

        var response: HashMap<String, Any> = HashMap()

        if ( user == null ) {
            response.put("accessToken", "")
            response.put("refreshToken", "")

        } else {
            response = authService.getAuthTokens(user)

            userTokenService.saveUserToken(user, null, response["refreshToken"].toString())
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(response)
    }





}
