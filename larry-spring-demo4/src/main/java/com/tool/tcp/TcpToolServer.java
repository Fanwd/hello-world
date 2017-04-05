package com.tool.tcp;

import com.tool.common.FwdUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by guoxiaomin on 2017/4/4.
 */
public class TcpToolServer {

    //端口
    private int port;
    //自动添加长度占位数 default:8
    private int pkglength;
    //发送/接受报文编码 default:UTF-8
    private String charset;
    //服务端实例
    private ServerSocket server;
    //客户端
    private Socket client;

    /**
     * 初始化
     *
     * @param port
     * @throws IOException
     */
    public TcpToolServer(int port) throws IOException {
        this(port, 8);
    }

    /**
     * 初始化
     * @param port
     * @param charset
     * @throws IOException
     */
    public TcpToolServer(int port, String charset) throws IOException {
        this(port, 8, charset);
    }

    public TcpToolServer(int port, int pkglength) throws IOException {
        this(port, pkglength, "UTF-8");
    }

    /**
     * 初始化服务端
     * @param port
     * @param pkglength
     * @param charset
     * @throws IOException
     */
    public TcpToolServer(int port, int pkglength, String charset) throws IOException {
        this.port = port;
        this.pkglength = pkglength;
        this.charset = charset;
        server = new ServerSocket(port);
    }

    /**
     *
     * @return
     */
    public int getPkglength() {
        return pkglength;
    }

    public void setPkglength(int pkglength) {
        this.pkglength = pkglength;
    }

    /**
     * 获取客户端ip地址
     * @param client
     * @return
     */
    public String getClientAddr(Socket client){
        InetAddress inetAddress = client.getInetAddress();
        String clientAddr = inetAddress.getHostAddress();
        return clientAddr;
    }

    /**
     * 接受连接
     * @throws IOException
     */
    private void accept() throws IOException {
        client = server.accept();
        System.out.println("建立连接["+getClientAddr(client)+"]");
    }

    /**
     * 关闭客户端连接
     * @throws IOException
     */
    private void closeClient() throws IOException {
        System.out.println("关闭连接["+getClientAddr(client)+"]");
        client.close();
    }

    /**
     * 接受数据
     *
     * @return
     * @throws IOException
     */
    public String read() throws IOException {
        accept();
        InputStream is = client.getInputStream();
        byte[] lenByte = new byte[pkglength];
        is.read(lenByte);
        String lenStr = new String(lenByte, charset);
        int len = Integer.parseInt(lenStr);
        byte[] dataByte = new byte[len];
        is.read(dataByte);
        String dataStr = new String(dataByte, charset);
        System.out.println("接收:["+getClientAddr(client)+"][" + lenStr + dataStr + "]");
        return lenStr + dataStr;
    }

    /**
     * 发送数据并关闭client连接
     *
     * @param data
     * @throws IOException
     */
    public void send(String data) throws IOException {
        System.out.println("发送:["+getClientAddr(client)+"]"+"["+data+"]");
        OutputStream os = client.getOutputStream();
        os.write(data.getBytes(charset));
        os.flush();
        closeClient();
    }

    /**
     * 为数据添加8位长度，并发送
     *
     * @param data
     * @throws IOException
     */
    public void sendAddLen(String data) throws IOException {
        data = String.format("%0" + pkglength + "d", data.getBytes(charset).length) + data;
        send(data);
    }

    /**
     * 停止监听服务
     */
    public void close() throws IOException {
        server.close();
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        TcpToolServer ttS = null;
        try {
            ttS = new TcpToolServer(9898);
            while (true) {
                String data = ttS.read();
                String pkg = FwdUtil.readFile("E:/socket.txt", "UTF-8");
                ttS.sendAddLen(pkg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                ttS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
