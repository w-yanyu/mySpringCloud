package com.example.remote.service;

import com.example.remote.annotations.RemoteChannel;
import com.example.remote.annotations.ServiceCode;

import java.util.Map;

@RemoteChannel("bairong")
public interface Bairong {
    @ServiceCode("tax")
    Map<String,Object> taxInfo(Map<String,Object> input);

    Map<String,Object> enterpriseInfo(Map<String,Object> input);
}
