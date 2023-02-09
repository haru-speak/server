package com.example.be.core.application.dto.response;

import java.util.List;
import lombok.Getter;

@Getter
public class StudyPreviewsResponse {

    private final List<StudyPreviewResponse> studyPreviewResponses;

    public StudyPreviewsResponse(List<StudyPreviewResponse> studyPreviewResponses) {
        this.studyPreviewResponses = studyPreviewResponses;
    }
}
