package com.totrade.cn.community.controller;

import com.totrade.cn.community.dto.AccessTokenDTO;
import com.totrade.cn.community.dto.GitHubUser;
import com.totrade.cn.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    @GetMapping("/callback")
    public  String callback(@RequestParam(name = "code") String code,@RequestParam(name = "state") String state){
        AccessTokenDTO dto = new AccessTokenDTO();
        dto.setCode(code);
        dto.setClient_id("e06d3f8aba3cca2e35c2");
        dto.setClient_secret("23bf954213b0a502368db087b8912d7926b4fc5c");
        dto.setRedirect_uri("http://localhost:80/callback");
        dto.setState(state);
        String accessToken = gitHubProvider.getAccessToken(dto);
        GitHubUser user = gitHubProvider.getGitHubUser(accessToken);
        System.out.println(user.getName());
        System.out.println(user.getId());
        return "index";
    }
}
