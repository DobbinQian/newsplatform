package com.qdbgame.newsplatform.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qdbgame.newsplatform.entities.CommonResult;
import com.qdbgame.newsplatform.tools.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * @author ：QDB
 * @date ：Created in 2020/10/11 12:56
 */

@Slf4j
@Component
public class JwtTokenFilter implements GlobalFilter, Ordered {

    @Resource
    ObjectMapper objectMapper;

    @Value("${secretKey:123456}")
    private String secretKey;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        String uri = serverHttpRequest.getURI().getPath();
        if (uri.indexOf("/user/loginOrRegister") >= 0) {
            return chain.filter(exchange);
        }
        String token = serverHttpRequest.getHeaders().getFirst("token");
        if (StringUtils.isBlank(token)) {
            serverHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
            return authErro(serverHttpResponse,"认证失败");
        }

        try {
            JWTUtil.verifyToken(token, secretKey);
        } catch (Exception ex) {
            return authErro(serverHttpResponse,"认证失败");
        }

        String userId = JWTUtil.getUserInfo(token);

        System.out.println(userId);

        Consumer<HttpHeaders> httpHeaders = httpHeader -> {
            httpHeader.set("userId", userId);
        };
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().headers(httpHeaders).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();

        return chain.filter(mutableExchange);
    }

    /**
     * 认证错误输出
     * @param resp 响应对象
     * @param mess 错误信息
     * @return
     */
    private Mono<Void> authErro(ServerHttpResponse resp,String mess) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        CommonResult<String> returnData = new CommonResult<>(org.apache.http.HttpStatus.SC_UNAUTHORIZED, mess, mess);
        String returnStr = "";
        try {
            returnStr = objectMapper.writeValueAsString(returnData);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(),e);
        }
        DataBuffer buffer = resp.bufferFactory().wrap(returnStr.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
