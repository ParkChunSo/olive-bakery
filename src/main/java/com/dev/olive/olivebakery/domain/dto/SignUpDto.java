package com.dev.olive.olivebakery.domain.dto;

import com.dev.olive.olivebakery.domain.entity.Member;
import lombok.*;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class SignUpDto {
    private String email;
    private String pw;
    private String name;
    private String phoneNumber;

    public Member toEntity(){
        return Member.builder()
                .email(email)
                .pw(pw)
                .name(name)
                .phoneNumber(phoneNumber)
                .stamp(0)
                .build();
    }
}
