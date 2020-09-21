package com.wang.server;

import com.sun.net.httpserver.HttpServer;
import com.wang.handler.SubmitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;

@Component
public class httpServer {


    @Autowired
    private SubmitHandler submitHandler;

    public void run() throws IOException{
        HttpServer createServer = HttpServer.create(new InetSocketAddress(8000), 0);
        createServer.createContext("/Access/create", submitHandler);
        createServer.start();
    }
}
