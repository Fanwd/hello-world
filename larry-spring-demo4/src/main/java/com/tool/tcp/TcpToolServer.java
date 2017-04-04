package com.tool.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by guoxiaomin on 2017/4/4.
 */
public class TcpToolServer {

    private int port;
    private ServerSocket server;
    private Socket client;

    public TcpToolServer(int port) throws IOException {
        this.port = port;
        server = new ServerSocket(port);
    }

    public void accept() throws IOException {
        client = server.accept();
    }

    public String read() throws IOException {
        InputStream is = client.getInputStream();
        byte[] lenByte = new byte[8];
        is.read(lenByte);
        String lenStr = new String(lenByte, "UTF-8");
        int len = Integer.parseInt(lenStr);
        byte[] dataByte = new byte[len];
        is.read(dataByte);
        String dataStr = new String(dataByte, "UTF-8");
        return lenStr + dataStr;
    }

    public void send(String data) throws IOException {
        OutputStream os = client.getOutputStream();
        os.write(data.getBytes("UTF-8"));
        os.flush();
        client.close();
    }

    public void close(){
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TcpToolServer ttS = null;
        try {
            ttS = new TcpToolServer(9898);
            while (true) {
                ttS.accept();
                String data = ttS.read();
                System.out.println("data:"+data);
                ttS.send("00000002OK");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            ttS.close();
        }
    }

}
