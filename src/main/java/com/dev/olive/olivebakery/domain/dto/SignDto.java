package com.dev.olive.olivebakery.domain.dto;

import com.dev.olive.olivebakery.domain.entity.Member;
import lombok.*;

public class SignDto {

    @Getter @NoArgsConstructor
    public static class SignIn {
        private String id;
        private String pw;
    }

    @Getter
    @NoArgsConstructor
    public static class SignUp {
        private String email;
        private String pw;
        private String name;
        private String phoneNumber;

        @Builder
        public SignUp(String email, String pw, String name, String phoneNumber){
            this.email = email;
            this.pw = pw;
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

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
}
