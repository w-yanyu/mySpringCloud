package org.remote.spring.test.service;

import org.remote.annotation.RemoteChannel;
import org.springframework.stereotype.Component;

@RemoteChannel("bairong")
@Component
public interface Bairong {
    void bairong001();

    void bairong002();
}
