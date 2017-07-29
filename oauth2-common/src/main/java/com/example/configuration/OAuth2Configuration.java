package com.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/7/28 18:00
 * Description:
 */
@EnableOAuth2Client
@Configuration
public class OAuth2Configuration {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    OAuth2ClientContext clientContext;

    String Client_ID = "00cf4d5797929f0556d2";
    String Client_Secret = "aaa93eb63de719f44358483733912895e1eb2416";
    String authorization_base_url = "https://github.com/login/oauth/authorize";
    String token_url = "https://github.com/login/oauth/access_token";
    String userInfo = "https://api.github.com/user";

    @Bean(name = "loginUrlFilterEntryPoint")
    public AuthenticationEntryPoint customizeLoginUrlAuthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/login.html");
    }

    @Bean(name = "curAppId")
    public String getAppId() {
        return Foundation.app().getAppId();
    }

    @Bean(name = "userLogInFilter")
    public Filter userLogInFilter() {

        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(ssoFilter(github(), "/login/github"));
        filter.setFilters(filters);
        return filter;
    }

    private Filter ssoFilter(ClientResources client, String path) {
        OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationFilter = new OAuth2ClientAuthenticationProcessingFilter(path);
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(client.getClient(), clientContext);
        oAuth2ClientAuthenticationFilter.setRestTemplate(oAuth2RestTemplate);
        UserInfoTokenServices tokenServices = new CustomizeUserInfoTokenServices(client.getName(),
                client.getResource().getUserInfoUri(),
                client.getClient().getClientId());
        tokenServices.setRestTemplate(oAuth2RestTemplate);
        oAuth2ClientAuthenticationFilter.setTokenServices(tokenServices);
        oAuth2ClientAuthenticationFilter.setAuthenticationManager(authenticationManager);
        return oAuth2ClientAuthenticationFilter;
    }

    class ClientResources {
        private final String name;
        private AuthorizationCodeResourceDetails client;

        private ResourceServerProperties resource;

        ClientResources(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setClient(AuthorizationCodeResourceDetails client) {
            this.client = client;
        }

        public void setResource(ResourceServerProperties resource) {
            this.resource = resource;
        }

        public AuthorizationCodeResourceDetails getClient() {
            return client;
        }

        public ResourceServerProperties getResource() {
            return resource;
        }
    }

    public ClientResources github() {
        AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();
        client.setAccessTokenUri(token_url);
        client.setClientId(Client_ID);
        client.setClientSecret(Client_Secret);
        client.setUserAuthorizationUri(authorization_base_url);
        client.setClientAuthenticationScheme(AuthenticationScheme.form);
        ClientResources clientResources = new ClientResources("github");
        clientResources.setClient(client);
        ResourceServerProperties resources = new ResourceServerProperties();
        resources.setUserInfoUri(userInfo);
        clientResources.setResource(resources);
        return clientResources;
    }


}
