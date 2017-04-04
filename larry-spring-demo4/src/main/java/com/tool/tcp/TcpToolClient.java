package com.tool.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by guoxiaomin on 2017/4/4.
 */
public class TcpToolClient {

    private String addr;
    private int port;
    Socket client;

    TcpToolClient(String addr, int port) throws IOException {
        this.addr = addr;
        this.port = port;
        client = new Socket(addr, port);
    }

    public String send(String pkg) throws IOException {
        OutputStream os = client.getOutputStream();
        os.write(pkg.getBytes("UTF-8"));
        os.flush();
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

    public String sendAddLen(String pkg) throws IOException {
        int pkgLen = pkg.length();
        String sendLen = String.format("%08d", pkgLen);
        pkg = pkgLen + pkg;
        OutputStream os = client.getOutputStream();
        os.write(pkg.getBytes("UTF-8"));
        os.flush();
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

    public void close(){
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TcpToolClient ttC = null;
        try {
            ttC = new TcpToolClient("127.0.0.1", 9898);
            String retStr = ttC.sendAddLen("00000005Hello");
            System.out.println(retStr);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            ttC.close();
        }

    }

}
