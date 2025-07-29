package org.remote.spring.test.service;

import org.remote.annotation.RemoteChannel;
import org.springframework.stereotype.Component;

@RemoteChannel("pudao")
public interface Pudao {
    void pudao001();
    void pudao002();
}
