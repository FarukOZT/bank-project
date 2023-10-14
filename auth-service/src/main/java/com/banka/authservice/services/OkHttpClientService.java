package com.banka.authservice.services;

import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
public interface OkHttpClientService {
    Response mailSend(Request request) throws IOException;
}
