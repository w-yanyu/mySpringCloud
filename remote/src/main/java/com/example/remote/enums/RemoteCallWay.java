package com.example.remote.enums;

import com.example.remote.invocationHandler.RemoteChannelHandler;
import com.example.remote.invocationHandler.impl.customize.CustomizeRemoteChannelHandler;
import com.example.remote.invocationHandler.impl.http.HttpRemoteChannelHandler;
import com.example.remote.invocationHandler.impl.netty.NettyRemoteChannelHandler;
import com.example.remote.invocationHandler.impl.rest.RestRemoteChannelHandler;
import com.example.remote.invocationHandler.impl.rpc.RpcRemoteChannelHandler;

public enum RemoteCallWay {

    RPC("rpc", new RpcRemoteChannelHandler()),
    HTTP("http", new HttpRemoteChannelHandler()),
    REST("rest", new RestRemoteChannelHandler()),
    NETTY("netty", new NettyRemoteChannelHandler()),
    CUSTOMIZE("customize", new CustomizeRemoteChannelHandler());
    private final String way;

    private final RemoteChannelHandler handler;

    private RemoteCallWay(String way, RemoteChannelHandler handler) {
        this.way = way;
        this.handler = handler;
    }

    public String getWay() {
        return way;
    }

    public RemoteChannelHandler getHandler() {
        return handler;
    }

}
