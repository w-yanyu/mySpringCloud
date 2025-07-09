# Remote Service Framework
> 这个项目提供了一个框架，用于在微服务架构中动态增强远程服务接口。它支持远程服务的自动注册、方法拦截以及动态代理生成。

## 目录
- [项目概述](#项目概述)
- [功能特点](#功能特点)
- [安装与配置](#安装与配置)
- [使用示例](#使用示例)

## 项目概述
这个项目是一个简化远程服务交互和增强服务接口的框架，适用于微服务。它提供了以下功能：
- **动态代理创建**：自动为远程服务接口创建代理。
- **服务增强**：通过拦截器允许方法级别的增强。
- **自动服务注册**：支持按照可配置的渠道自动注册远程服务。

## 功能特点
- **接口增强**：使用注解动态增强服务接口。
- **远程通信支持**：支持多种远程服务调用协议。
- **自定义拦截器**：支持自定义拦截器来增强服务方法，实现如日志、度量等功能。
- **基于渠道的配置**：服务可以按渠道分组，从而实现对服务行为的精细控制。

## 安装与配置
### 前提条件
- Java 1.8 或更高版本

## 使用示例
定义一个需要增强处理的接口类。
```java
@RemoteChannel("testChannel001")
public interface TestChannel {

    @RemoteServiceCode("request01")
    void method01(String[] args);

    void method02(String[] args);
}
```

定义一个对接口方法功能实现的代理处理类。
```java
public class TestRemoteChannelHandler implements RemoteChannelHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("test ..." + method);
        return new Object();
    }
}
```

定义一个对接口方法整体增强的代理类。使用了@RemoteEnhancer(value = "testChannel001", methods = {"method02"}),因此只会对 testChannel001 渠道下的 method02 做处理。
```java
@RemoteEnhancer(value = "testChannel001", methods = {"method02"})
public class TestLogRemoteProxyEnhancer implements RemoteProxyEnhancer {

    @Override
    public Object before(String channel, String methodName, Object proxy, Method method, Object[] args) {
        System.out.println("channel:" + channel);
        System.out.println("methodName:" + methodName);
        System.out.println("parameter:" + Arrays.toString(args));
        return null;
    }

    @Override
    public Object after(String channel, String methodName, Object proxy, Method method, Object[] args, Object result) {
        System.out.println("channel:" + channel);
        System.out.println("methodName:" + methodName);
        System.out.println("parameter:" + Arrays.toString(args));
        System.out.println("result:" + result);
        return null;
    }
}
```
测试类
```java
public class Test {
    public static void main(String[] args) {
        TestChannel02 testChannel2 = Remote.getInstance(TestChannel02.class);
        testChannel2.method01(new String[]{});
        System.out.println("========================================");
        testChannel2.method02(new String[]{});
    }
}
```

在配置文件下注册以上增强处理等。
- 在 /resources 目录下新建 META-INF/services 目录。
- 新建以"com.example.remote.enhancer.RemoteProxyEnhancer"命名的文件，用来注册增强类。
```text
TestLogRemoteProxyEnhancer
```
- 新建以"com.example.remote.invocationHandler.RemoteChannelHandler"命名的文件，用来注册实现类。
```text
TestRemoteChannelHandler
```
- 在 application.properties 配置文件中将 “remote.channel.handler” 设置为 “customize”。
```text
remote.channel.handler=customize
```
对应目录结构如下：
```perl
├──resources
    ├──META-INF
        ├──services
            ├──com.example.remote.enhancer.RemoteProxyEnhancer
            ├──com.example.remote.invocationHandler.RemoteChannelHandler
    ├──application.properties
```

```bash
mvn install:install-file -DgroupId=org.remote -DartifactId=remote -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar -Dfile=./remote-0.0.1-SNAPSHOT.jar -DlocalRepositoryPath=C:/mvnrepository
```