package com.example.be.core.web.member;

import static com.example.be.common.response.ResponseCodeAndMessages.JOIN_SUCCESS;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.dto.request.MemberFormRequest;
import com.example.be.core.application.dto.response.MemberResponse;
import com.example.be.core.web.member.InitMemberControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

public class MemberControllerJoinTest extends InitMemberControllerTest {

  @Nested
  @DisplayName("회원가입 할 때")
  class joinTest {

    @Nested
    @DisplayName("정상적인 요청이라면")
    class NormalTest {

      @Test
      @DisplayName("회원가입 완료 시, 해당 email을 가진 멤버가 생성된다.")
      void join_member() throws Exception {
        //given
        Long memberId = 3L;
        MemberFormRequest request = new MemberFormRequest("승연", "tmddus@naver.com", "1234");
        MemberResponse response = new MemberResponse( memberId, "승연", "tmddus@naver.com","1234");
        BaseResponse<MemberResponse> baseResponse = new BaseResponse<>(JOIN_SUCCESS, response);

        when(memberService.create(refEq(request)))
            .thenReturn(response);

        //when
        ResultActions resultActions = mockMvc.perform(post("/join")
            .content(objectMapper.writeValueAsString(request))
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions.andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));

        verify(memberService).create(refEq(request));
      }
    }
  }

}
