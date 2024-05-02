package com.trainh.tcookbe.payload.request.introduction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IntroductionBlogCreationRequest {
    private long id;
    private String content;
}
