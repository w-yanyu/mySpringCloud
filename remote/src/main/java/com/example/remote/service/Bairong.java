package com.example.remote.service;

import com.example.remote.annotations.RemoteChannel;
import com.example.remote.annotations.RemoteServiceCode;

import java.util.Map;

@RemoteChannel("bairong")
public interface Bairong {
    @RemoteServiceCode("tax")
    Map<String,Object> taxInfo(Map<String,Object> input);

    Map<String,Object> enterpriseInfo(Map<String,Object> input);
}
