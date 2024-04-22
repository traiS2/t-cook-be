package com.trainh.tcookbe.payload.request.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TagBlogCreationRequest {
    private int id;
    private String name;
    private boolean isNew;
}
