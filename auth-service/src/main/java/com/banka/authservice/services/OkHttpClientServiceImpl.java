package com.banka.authservice.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OkHttpClientServiceImpl  {

    @Autowired
    private OkHttpClient okHttpClient;

    public Response mailSend(Request request) throws IOException {
        return okHttpClient.newCall(request).execute();
    }
}
