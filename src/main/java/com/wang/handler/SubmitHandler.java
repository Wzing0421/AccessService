package com.wang.handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.net.InetAddress;

import com.wang.udpserver.udpServer;

import javax.annotation.Resource;

@Component
public class SubmitHandler extends BaseHttpHandler{
    private Map<Integer, String> IPPortMap = new TreeMap<Integer, String>();

    @Override
    protected void doHandlePut(Map<String, String> parameters) throws Exception {
        return;
    }

    @Override
    protected String doHandlePost(Map<String, String> parameters) throws Exception {
        String IP = InetAddress.getLocalHost().getHostAddress();
        //分配一个jodId和ipPort: port = 8000 + jobId
        int i = 1;
        int jobId;
        int port;
        for(Object o : IPPortMap.keySet()){
            if(i != (int)o){
                jobId = i;
                port = 8000 + jobId;
                String IPPort = IP + "_" + Integer.toString(port);
                IPPortMap.put(jobId, IPPort);
                return Integer.toString(jobId) + "_" + IPPort;
            }
            i++;
        }
        jobId = IPPortMap.size() + 1;
        port = jobId + 8000;
        String IPPort = IP + "_" + Integer.toString(port);
        IPPortMap.put(jobId, IPPort);

        //启动一个udpServer线程
        new Thread(new udpServer(port)).start();
        return Integer.toString(jobId) + "_" + IPPort;
    }
}
