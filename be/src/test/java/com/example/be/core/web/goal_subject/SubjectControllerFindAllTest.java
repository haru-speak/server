package com.example.be.core.web.goal_subject;

import static com.example.be.common.response.ResponseCodeAndMessages.FIND_ALL_SUBJECTS_SUCCESS;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.be.common.response.BaseResponse;
import com.example.be.core.application.dto.response.SubjectResponse;
import com.example.be.core.application.member.SubjectService;
import com.example.be.core.domain.member.subject.Subject;
import com.example.be.core.web.InitControllerTest;
import com.example.be.core.web.member.SubjectController;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(SubjectController.class)
@DisplayName("컨트롤러 테스트 : Subject 전체 조회")
class SubjectControllerFindAllTest extends InitControllerTest {

	@MockBean
	private SubjectService subjectService;

	private static final String staticImageUrl = "https://haru-speak-s3.s3.ap-northeast-2.amazonaws.com/image/static/";
	private static final String staticImageExt = ".png";
	private static final List<SubjectResponse> subjects = Arrays.asList(
		SubjectResponse.of(new Subject(1L, "여행", staticImageUrl + "travle" + staticImageExt)),
		SubjectResponse.of(new Subject(2L, "영화&음악", staticImageUrl + "movie_music" + staticImageExt)),
		SubjectResponse.of(new Subject(3L, "일&회사", staticImageUrl + "company" + staticImageExt)),
		SubjectResponse.of(new Subject(4L, "쇼핑", staticImageUrl + "shopping" + staticImageExt)),
		SubjectResponse.of(new Subject(5L, "음식", staticImageUrl + "food" + staticImageExt)),
		SubjectResponse.of(new Subject(6L, "가족&친구", staticImageUrl + "family" + staticImageExt)),
		SubjectResponse.of(new Subject(7L, "운동&건강", staticImageUrl + "workout" + staticImageExt)),
		SubjectResponse.of(new Subject(8L, "동네", staticImageUrl + "town" + staticImageExt)),
		SubjectResponse.of(new Subject(9L, "연애", staticImageUrl + "love" + staticImageExt))
	);

	@Nested
	@DisplayName("Subject 전체 조회를 할 때")
	class RetrieveAllTest {

		@Nested
		@DisplayName("정상적인 요청이라면")
		class NormalTest {

			@Test
			@DisplayName("모든 Subject들이 조회된다.")
			void find_all_subjects() throws Exception {
				//given
				Long memberId = 1L;
				BaseResponse<List<SubjectResponse>> baseResponse = new BaseResponse<>(FIND_ALL_SUBJECTS_SUCCESS, subjects);
				when(subjectService.findAll()).thenReturn(subjects);

				//when
				ResultActions resultActions = mockMvc.perform(get("/subject")
					.header("Authorization", "Bearer " + jwtProvider.generateAccessToken(memberId))
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print());

				//then
				resultActions.andExpect(status().isOk())
					.andExpect(content().string(objectMapper.writeValueAsString(baseResponse)));
			}
		}
	}

}
