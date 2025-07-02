package com.example.remote.service;

import com.example.remote.annotations.RemoteChannel;
import com.example.remote.annotations.RemoteServiceCode;

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
