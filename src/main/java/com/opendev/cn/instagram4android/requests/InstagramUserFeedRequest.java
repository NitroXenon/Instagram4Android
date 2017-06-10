package com.opendev.cn.instagram4android.requests;

import com.opendev.cn.instagram4android.requests.payload.InstagramFeedResult;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by root on 09/06/17.
 */

@AllArgsConstructor
@RequiredArgsConstructor
public class InstagramUserFeedRequest extends InstagramGetRequest<InstagramFeedResult> {

    @NonNull
    private long userId;
    private String maxId;
    private long minTimestamp;


    @Override
    public String getUrl() {
        return "feed/user/" + userId + "/?max_id=" + maxId + "&min_timestamp=" + minTimestamp + "&rank_token=" + api.getRankToken() + "&ranked_content=true&";
    }

    @Override
    @SneakyThrows
    public InstagramFeedResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramFeedResult.class);
    }

}
