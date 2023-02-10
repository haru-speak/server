package com.example.be.core.application.study;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.be.core.application.InitServiceTest;
import com.example.be.core.application.dto.request.StudyPreviewConditionRequest;
import com.example.be.core.application.dto.response.StudyPreviewsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("서비스 테스트 : Study Preview 조회")
public class StudyFindPreviewTest extends InitServiceTest {

    @Nested
    @DisplayName("Study Preview 조회할 때")
    class FindPreviewTest {

        @Nested
        @DisplayName("정상적인 요청이라면")
        class NormalTest {

            @Test
            @DisplayName("MY 타입으로 Study Preview 조회 시, MY 타입 Study Preview 를 조회한다.")
            void findMyPreview() {
                //given
                Long memberId = 1L;
                StudyPreviewConditionRequest request = new StudyPreviewConditionRequest("MY");

                //when
                StudyPreviewsResponse response = studyService.findPreview(memberId, request);

                //then
                assertThat(response.getStudyPreviewResponses()).hasSize(4);
            }

            @Test
            @DisplayName("RANDOM 타입으로 Study Preview 조회 시, RANDOM 타입 Study Preview 를 조회한다.")
            void findRandomPreview() {
                //given
                Long memberId = 1L;
                StudyPreviewConditionRequest request = new StudyPreviewConditionRequest("RANDOM");

                //when
                StudyPreviewsResponse response = studyService.findPreview(memberId, request);

                //then
                assertThat(response.getStudyPreviewResponses()).hasSize(4);
                assertThat(response.getStudyPreviewResponses().get(0).getStudyId()).isEqualTo(4L);
            }
        }
    }
}
