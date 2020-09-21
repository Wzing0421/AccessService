package com.wang.udpserver;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class udpServer extends Thread{

    private DatagramSocket datagramSocket;

    public byte[] buf;

    public udpServer(int port) throws Exception{
        datagramSocket = new DatagramSocket(port);
        buf = new byte[65536];
    }

    @lombok.SneakyThrows
    public void run(){
        System.out.println(datagramSocket.getPort());
        while (true){
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            datagramSocket.receive(packet);
            String ip = "162.105.84.182";
            int port = 8080;
            DatagramPacket packetSend = new DatagramPacket(packet.getData(), packet.getLength(), InetAddress.getByName(ip), port);
            datagramSocket.send(packetSend);
        }
    }
}
