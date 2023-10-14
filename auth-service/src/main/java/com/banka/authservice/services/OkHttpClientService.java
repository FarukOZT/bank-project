package com.pia.sot.postpaid.service;

import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public interface OkHttpClientService {
    Response createOrderRequest(Request request) throws IOException;
}
