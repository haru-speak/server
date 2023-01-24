package com.example.be.core.application.dto.request;

import com.example.be.core.repository.member.MemberRepository;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberFormRequest {

  @NotBlank(message = "이름은 필수 입력 값입니다.")
  @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
  private String nickname;

  @NotBlank(message = "이메일은 필수 입력 값입니다.")
  @Email(message = "이메일 형식으로 입력해주세요.")
  private String email;

  @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
  @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
  @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\\\d)(?=.*\\\\W).{4,16}$", message = "비밀번호는 4~16자로, 영문, 숫자, 특수문자를 이용하여 구성해주세요.")
  private String password;

  public MemberFormRequest(String nickname, String email, String password, PasswordEncoder passwordEncoder) {
    this.nickname = nickname;
    this.email = email;
    this.password = passwordEncoder.encode(password); // DTO에서 password 인코딩
  }

  @Override
  public String toString() {
    return "nickname: " + nickname + ", email: " + email + ", password: " + password;
  }
}
