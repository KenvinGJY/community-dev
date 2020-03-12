package com.totrade.cn.community.provider;

import com.alibaba.fastjson.JSON;
import com.totrade.cn.community.dto.AccessTokenDTO;
import com.totrade.cn.community.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class GitHubProvider {
    private static final String URL = "https://github.com/login/oauth/access_token";
    private static final String USER_URL = "https://api.github.com/user?access_token=";
    public String getAccessToken(AccessTokenDTO dto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(dto));
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String reuslt = response.body().string();
            //access_token=ad53aecf1ec9221e4219d0148f2a3024e8ba18fd&scope=user&token_type=bearer
            String[] split = reuslt.split("&");
            String token = split[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitHubUser getGitHubUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(USER_URL+accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            GitHubUser user= JSON.parseObject(result, GitHubUser.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
