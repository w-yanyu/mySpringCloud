package com.example.remote.service;

import com.example.remote.annotation.RemoteChannel;
import com.example.remote.annotation.RemoteServiceCode;

import java.util.Map;

/**
 *
 */
@RemoteChannel
public interface Pudao {

    @RemoteServiceCode
    Map<String,Object> taxInformation(Map<String,Object> input);


    Map<String,Object> enterpriseInformation(Map<String,Object> input);
}
