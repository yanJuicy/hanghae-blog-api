package com.hanghae.blog.api.like.dto.comments;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeState {

    private boolean likeState;

    public LikeState(boolean likeState) {
        this.likeState = likeState;
    }
}
