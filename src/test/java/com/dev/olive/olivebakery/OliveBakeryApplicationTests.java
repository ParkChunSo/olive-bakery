//package com.dev.olive.olivebakery;
//
//import com.dev.olive.olivebakery.domain.dto.SignInDto;
//import com.dev.olive.olivebakery.domain.dto.SignUpDto;
//import com.dev.olive.olivebakery.domain.entity.Member;
//import com.dev.olive.olivebakery.repository.MemberRepository;
//import com.dev.olive.olivebakery.service.SignService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.Matchers.hasItem;
//import static org.junit.Assert.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class OliveBakeryApplicationTests {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private SignService signService;
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private MockMvc mockMvc;
//
//    SignUpDto signupDto;
//    String pw;
//
//    @Test
//    public void contextLoads() {
//    }
//
//    @Test
//    public void encoderTest(){
//        String s = "asdf";
//        pw = passwordEncoder.encode(s);
//
//
//        assertThat(passwordEncoder.matches(s, pw), is(true));
//
////        for(int i = 0 ; i < 10 ; i++)
////            System.out.println(passwordEncoder.encode(s));
//
//    }
//
//    @Test
//    public void setUser(){
//        signupDto = SignUpDto.builder()
//                .email("cnsth123@email.com")
//                .pw("pw")
//                .name("박춘소")
//                .phoneNumber("010-9128-4939")
//                .build();
//    }
//
//    @Test
//    public void createUserTest() {
//        signupDto = SignUpDto.builder()
//                .email("cnsth123@email.com")
//                .pw("pass1")
//                .name("박춘소")
//                .phoneNumber("010-9128-4939")
//                .build();
//
//        signService.signUp(signupDto, "CLI");
//        Member member = memberRepository.findByEmail(signupDto.getEmail()).orElseThrow();
//        assertThat(member.getEmail(), is(signupDto.getEmail()));
//
//        assertThat(passwordEncoder.matches("pass1", member.getPw()), is(true));
//
//        System.out.println(member.getRole());
//        /*Collection<? extends GrantedAuthority> authorities1 = member.getRole();
//        Iterator<? extends GrantedAuthority> it = authorities1.iterator();
//        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) user.getAuthorities();
//        while (it.hasNext()) {
//            GrantedAuthority authority = it.next();
//            assertThat(authorities, hasItem(new SimpleGrantedAuthority(authority.getAuthority())));
//        }*/
//    }
//    @Test
//    public void loginTest() throws Exception {
//        SignInDto request = new SignInDto();
//        request.setId("user1");
//        request.setPw("pass1");
//
//        ObjectMapper om = new ObjectMapper();
//
//        mockMvc.perform(post("/olive/user/login")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(om.writeValueAsString(request)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$.username", is(request.getId())))
//                .andExpect(jsonPath("$.authorities[*].authority", hasItem("CLIENT")))
//        ;
//    }
//
//}
//
