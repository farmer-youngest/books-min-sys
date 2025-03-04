package com.marubi.security.core.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@Slf4j
public class MyOkHttpInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request();
        log.info("req->{}",request);
        log.info("chain->{}",chain);
        Response response = chain.proceed(request);
        log.info("response->{}",response);
        return response;
    }
}
