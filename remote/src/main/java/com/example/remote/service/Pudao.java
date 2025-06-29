package com.example.remote.service;

import com.example.remote.annotations.RemoteChannel;
import com.example.remote.annotations.ServiceCode;

import java.util.Map;

/**
 *
 */
@RemoteChannel
public interface Pudao {

    @ServiceCode
    Map<String,Object> taxInformation(Map<String,Object> input);


    Map<String,Object> enterpriseInformation(Map<String,Object> input);
}
