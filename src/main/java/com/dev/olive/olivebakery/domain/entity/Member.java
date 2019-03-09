package com.dev.olive.olivebakery.domain.entity;

import com.dev.olive.olivebakery.domain.enums.MemberRole;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
@Table(name = "member_tbl")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String pw;

    private String name;

    @Column(name = "phone_num")
    private String phoneNumber;

    private int stamp;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<MemberRole> role;

    public User toUser(){
        return new User(email, pw
                , role.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                    .collect(Collectors.toSet()));
    }
}
