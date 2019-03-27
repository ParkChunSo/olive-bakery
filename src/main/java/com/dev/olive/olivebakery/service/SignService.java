package com.dev.olive.olivebakery.service;

import com.dev.olive.olivebakery.domain.dto.SignDto;
import com.dev.olive.olivebakery.domain.entity.Member;
import com.dev.olive.olivebakery.domain.enums.MemberRole;
import com.dev.olive.olivebakery.exception.UserDefineException;
import com.dev.olive.olivebakery.repository.MemberRepository;
import com.dev.olive.olivebakery.utill.TokenUtills;
import lombok.extern.java.Log;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Log
public class SignService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public SignService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(member.getEmail(), member.getPw(), makeGrantedAuthority(member.getRole()));
    }

    private Set<GrantedAuthority> makeGrantedAuthority(Set<MemberRole> roles) {
        return roles.stream()
                .map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                .collect(Collectors.toSet());
    }

    public String signIn(SignDto.SignIn signInDto){
        Member member = memberRepository.findByEmail(signInDto.getId())
                .orElseThrow(() -> new UserDefineException("아이디를 잘못 입력하셨습니다."));

        if(!passwordEncoder.matches(signInDto.getPw(), member.getPw())){
            throw new UserDefineException("비밀번호를 잘못 입력하셨습니다.");
        }

        return TokenUtills.createToken(member.toUser());
    }

    public String signUp(SignDto.SignUp signupDto, String ROLE){
        if(memberRepository.findByEmail(signupDto.getEmail()).isPresent())
            throw new UserDefineException("아이디가 중복됩니다.");

        Member member = signupDto.toEntity();
        member.setPw(passwordEncoder.encode(member.getPw()));
        if(ROLE.equals(MemberRole.ADMIN.name()))
            member.setRole(Stream.of(MemberRole.ADMIN, MemberRole.CLIENT).collect(Collectors.toSet()));
        else
            member.setRole(Stream.of(MemberRole.CLIENT).collect(Collectors.toSet()));

        memberRepository.save(member);

        return TokenUtills.createToken(member.toUser());
    }

    public void update(SignDto.SignUp signupDto) {
        Member member = memberRepository.findByEmail(signupDto.getEmail())
                .orElseThrow(() -> new UserDefineException("아이디가 존재하지 않습니다."));
        memberRepository.save(member);
    }

    public void delete(SignDto.SignIn signInDto) {
        Member member = memberRepository.findByEmail(signInDto.getId())
                .orElseThrow(() -> new UserDefineException("아이디를 잘못 입력하셨습니다."));
        if(passwordEncoder.matches(signInDto.getPw(), member.getPw()))
            memberRepository.deleteById(member.getId());
        else
            throw new UserDefineException("비밀번호를 잘못 입력하셨습니다.");
    }

    public Member findById(String userId) {
        return memberRepository.findByEmail(userId)
                .orElseThrow(() -> new UserDefineException("해당 유저가 존재하지 않습니다."));
    }
}
