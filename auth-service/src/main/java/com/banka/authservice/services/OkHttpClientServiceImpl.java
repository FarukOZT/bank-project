package com.pia.sot.postpaid.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OkHttpClientServiceImpl implements OkHttpClientService {

    @Autowired
    OkHttpClient okHttpClient;

    @Override
    public Response createOrderRequest(Request request) throws IOException {
        return okHttpClient.newCall(request).execute();
    }
}
