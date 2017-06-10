package com.opendev.cn.instagram4android.requests;

import com.opendev.cn.instagram4android.InstagramConstants;
import com.opendev.cn.instagram4android.util.InstagramHashUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by root on 08/06/17.
 */

public abstract class InstagramPostRequest<T> extends InstagramRequest<T> {

    @Override
    public String getMethod() {
        return "POST";
    }

    @Override
    public T execute() throws IOException {

        Request request = new Request.Builder()
                .url(InstagramConstants.API_URL + getUrl())
                .addHeader("Connection", "close")
                .addHeader("Accept", "*/*")
                .addHeader("Cookie2", "$Version=1")
                .addHeader("Accept-Language", "en-US")
                .addHeader("User-Agent", InstagramConstants.USER_AGENT)
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), InstagramHashUtil.generateSignature(getPayload())))
                .build();

        Response response = api.getClient().newCall(request).execute();
        api.setLastResponse(response);

        int resultCode = response.code();
        String content = response.body().string();

        return parseResult(resultCode, content);
    }
}
