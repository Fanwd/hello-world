package com.tool.tcp;

import com.tool.common.FwdUtil;

import java.io.*;
import java.net.Socket;

/**
 * Created by guoxiaomin on 2017/4/4.
 */
public class TcpToolClient {

    private String addr;
    private int port;
    private int pkglength;
    private String charset;
    Socket client;

    /**
     * 初始化参数
     * @param addr IP地址
     * @param port 端口号
     * @throws IOException
     */
    public TcpToolClient(String addr, int port) throws IOException {
        this(addr, port, 8);
    }

    /**
     * 初始化参数
     * @param addr IP地址
     * @param port 端口号
     * @param charset 编码格式
     * @throws IOException
     */
    public TcpToolClient(String addr, int port, String charset) throws IOException {
        this(addr, port, 8, charset);
    }

    /**
     * 初始化参数
     * @param addr
     * @param port
     * @param pkglength
     * @throws IOException
     */
    public TcpToolClient(String addr, int port, int pkglength) throws IOException {
        this(addr, port, pkglength, "UTF-8");
    }

    /**
     * 初始化参数
     * @param addr
     * @param port
     * @param pkglength
     * @param charset
     * @throws IOException
     */
    public TcpToolClient(String addr, int port, int pkglength, String charset) throws IOException {
        this.addr = addr;
        this.port = port;
        this.pkglength = pkglength;
        this.charset = charset;
    }

    /**
     * 连接服务端
     * @throws IOException
     */
    private void connect() throws IOException {
        client = new Socket(addr, port);
        System.out.println("建立连接["+addr+":"+port+"]");
    }

    /**
     * 发送报文
     * @param pkg 报文内容
     * @return 返回内容
     * @throws IOException
     */
    public String send(String pkg) throws IOException {
        connect();
        System.out.println("发送:["+pkg+"]");
        OutputStream os = client.getOutputStream();
        os.write(pkg.getBytes(charset));
        os.flush();
        InputStream is = client.getInputStream();
        byte[] lenByte = new byte[pkglength];
        is.read(lenByte);
        String lenStr = new String(lenByte, charset);
        int len = Integer.parseInt(lenStr);
        byte[] dataByte = new byte[len];
        is.read(dataByte);
        String dataStr = new String(dataByte, charset);
        System.out.println("接收:["+lenStr+dataStr+"]");
        close();
        return lenStr + dataStr;
    }

    /**
     * 报文前添加报文总长度
     * @param pkg 报文内容
     * @return 返回报文
     * @throws IOException
     */
    public String sendAddLen(String pkg) throws IOException {
        pkg = String.format("%0"+pkglength+"d", pkg.getBytes(charset).length) + pkg;
        return send(pkg);
    }

    public static String sendTo(String addr, int port, String pkg) throws IOException {
        return sendTo(addr, port, pkg, "UTF-8");
    }

    public static String sendTo(String addr, int port, String pkg, String charset) throws IOException {
        TcpToolClient tcpToolClient = new TcpToolClient(addr, port, charset);
        return tcpToolClient.send(pkg);
    }

    public static String sendToAddLen(String addr, int port, String pkg) throws IOException {
        return sendToAddLen(addr, port, pkg, 8);
    }

    public static String sendToAddLen(String addr, int port, String pkg, String charset) throws IOException {
        return sendToAddLen(addr, port, pkg, 8, charset);
    }

    public static String sendToAddLen(String addr, int port, String pkg, int pkglength) throws IOException {
        return sendToAddLen(addr, port, pkg, pkglength, "UTF-8");
    }

    public static String sendToAddLen(String addr, int port, String pkg, int pkglength, String charset) throws IOException {
        TcpToolClient tcpToolClient = new TcpToolClient(addr, port, pkglength, charset);
        return tcpToolClient.sendAddLen(pkg);
    }

    /**
     * 关闭连接
     */
    private void close(){
        try {
            System.out.println("关闭连接["+addr+":"+port+"]");
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        TcpToolClient ttC = null;
        try {
            ttC = new TcpToolClient("192.168.4.13", 9898, "UTF-8");
//            String retStr = ttC.sendAddLen("idea添加类和方法注释的快捷键是什么");
            String pkg = FwdUtil.readFile("E:/socket.txt", "UTF-8");
            String retStr = ttC.sendAddLen(pkg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
