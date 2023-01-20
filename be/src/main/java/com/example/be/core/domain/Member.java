package com.example.be.core.domain;

import com.example.be.core.application.dto.request.MemberFormRequest;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "member_id")
  private Long id;

  private String nickname;

  @Column(unique = true)
  private String email;

  private String password;

  @Builder
  public Member(String nickname, String email, String password) {
    this.nickname = nickname;
    this.email = email;
    this.password = password;
  }

  public static Member createMember(MemberFormRequest memberFormRequest, PasswordEncoder passwordEncoder) {
    Member member = Member.builder()
        .nickname(memberFormRequest.getNickname())
        .email(memberFormRequest.getEmail())
        .password(passwordEncoder.encode(memberFormRequest.getPassword()))  // 암호화 처리
        .build();

    return member;
  }

}
