package com.example.ordersystem_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientThreadCode extends Thread {
    private Socket m_socket;//和伺服器端進行連線
    public String data;
    private String ip = "";
    private int port = 0;

    public ClientThreadCode(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            m_socket = new Socket(ip, port);
            //由於Server端使用 PrintStream 和 BufferedReader 來接收和寄送訊息，所以Client端也要相同
            PrintStream writer = new PrintStream(m_socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));

            writer.println("Today's Menu?");
            writer.flush();
            //System.out.println(reader.readLine());
            data = reader.readLine();
            m_socket.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    String getData() {
        return data;
    }

}