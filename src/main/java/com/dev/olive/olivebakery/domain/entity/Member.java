package com.dev.olive.olivebakery.domain.entity;

import com.dev.olive.olivebakery.domain.enums.MemberRole;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member_tbl")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "member")
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @Builder
    public Member(String email, String pw, String name, String phoneNumber, int stamp) {
        this.email = email;
        this.pw = pw;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.stamp = stamp;
    }

    public User toUser() {
        return new User(email, pw
                , role.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toSet()));
    }
}
