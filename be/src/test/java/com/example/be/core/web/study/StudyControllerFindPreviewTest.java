package com.example.be.core.web.study;

import static com.example.be.common.response.ResponseCodeAndMessages.FIND_STUDY_PREVIEW_SUCCESS;
import static org.mockito.Mockito.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.dto.request.StudyPreviewConditionRequest;
import com.example.be.core.application.dto.response.StudyPreviewsResponse;
import com.example.be.core.web.InitControllerTest;
import com.example.be.core.web.StudyController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(StudyController.class)
@DisplayName("컨트롤러 테스트 : Study Preview 조회")
public class StudyControllerFindPreviewTest extends InitControllerTest {

    @Nested
    @DisplayName("Study Preview 조회할 때")
    class FindPreview {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class NormalTest {

            @Test
            @DisplayName("MY 타입으로 Study Preview 조회 시, MY 타입 Study Preview 를 조회한다.")
            void findMyPreview() throws Exception {
                //given
                Long memberId = 1L;
                StudyPreviewConditionRequest request = new StudyPreviewConditionRequest("MY");
                StudyPreviewsResponse response = new StudyPreviewsResponse(null);

                BaseResponse<StudyPreviewsResponse> baseResponse = new BaseResponse<>(FIND_STUDY_PREVIEW_SUCCESS,response);

                when(studyService.findPreview(refEq(memberId), refEq(request)))
                    .thenReturn(response);

                //when
                ResultActions resultActions = mockMvc.perform(get("/study/preview?type=MY")
                    .header("Authorization", "Bearer " + jwtProvider.generateAccessToken(memberId))
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .contentType(MediaType.APPLICATION_JSON_VALUE));

                //then
                resultActions.andExpect(status().isOk())
                    .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
                    .andDo(print());

                verify(studyService).findPreview(refEq(memberId), refEq(request));
            }

            @Test
            @DisplayName("RANDOM 타입으로 Study Preview 조회 시, RANDOM 타입 Study Preview 를 조회한다.")
            void findRandomPreview() throws Exception {
                //given
                Long memberId = 1L;
                StudyPreviewConditionRequest request = new StudyPreviewConditionRequest("RANDOM");
                StudyPreviewsResponse response = new StudyPreviewsResponse(null);

                BaseResponse<StudyPreviewsResponse> baseResponse = new BaseResponse<>(FIND_STUDY_PREVIEW_SUCCESS,response);

                when(studyService.findPreview(refEq(memberId), refEq(request)))
                    .thenReturn(response);

                //when
                ResultActions resultActions = mockMvc.perform(get("/study/preview?type=RANDOM")
                    .header("Authorization", "Bearer " + jwtProvider.generateAccessToken(memberId))
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .contentType(MediaType.APPLICATION_JSON_VALUE));

                //then
                resultActions.andExpect(status().isOk())
                    .andExpect(content().string(objectMapper.writeValueAsString(baseResponse)))
                    .andDo(print());

                verify(studyService).findPreview(refEq(memberId), refEq(request));
            }
        }
    }
}

