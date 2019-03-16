package com.dev.olive.olivebakery.controller;

import com.dev.olive.olivebakery.domain.dto.SignInDto;
import com.dev.olive.olivebakery.domain.dto.SignUpDto;
import com.dev.olive.olivebakery.domain.enums.MemberRole;
import com.dev.olive.olivebakery.service.SignService;
import org.springframework.web.bind.annotation.*;

/**
 * 로그인 관련 Controller
 */

@RestController
@RequestMapping(value = "/olive/sign")
public class SignController {

    private final SignService signService;

    public SignController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpDto signupDto){
        return signService.signUp(signupDto, MemberRole.CLIENT.name());
    }

    @PostMapping
    public String signIn(@RequestBody SignInDto signInDto){
        return signService.signIn(signInDto);
    }

    @PutMapping
    public void update(@RequestBody SignUpDto signupDto){
        signService.update(signupDto);
    }

    @DeleteMapping
    public void delete(@RequestBody SignInDto signInDto){
        signService.delete(signInDto);
    }


    /*public AuthTokenDto login(@RequestBody AuthRequestDto authRequestDto, HttpSession session){
        String username = authRequestDto.getUsername();
        String password = authRequestDto.getPassword();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        Member member = loginService.getMemberById(username);
        return new AuthTokenDto(member.getId(), member.getRole(), session.getId());

    }
*/
}
