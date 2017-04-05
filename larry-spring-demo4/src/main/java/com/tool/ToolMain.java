package com.tool;

import com.tool.common.FwdUtil;
import com.tool.tcp.TcpToolClient;
import com.tool.tcp.TcpToolServer;

import java.io.IOException;

/**
 * Created by fanweidong on 2017/4/5.
 */
public class ToolMain {

    public static void main(String[] args) {
        //tcp客户端工具测试
//        tcpClientTest();
        tcpServerTest();
    }

    public static void tcpClientTest(){
        try {
//            String recStr = TcpToolClient.sendTo("192.168.1.112", 9898, "今天天气真好");
            //读文件内容
            String pkg = FwdUtil.readFile("E:/socket.txt", "UTF-8");
            //发送报文
            String retStr = TcpToolClient.sendToAddLen("192.168.4.13", 9898, pkg, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void tcpServerTest(){
        try {
            TcpToolServer tcpToolServer = new TcpToolServer(9898, 8, "UTF-8");
            while (true) {
                tcpToolServer.read();
                String pkg = FwdUtil.readFile("E:/socket.txt", "UTF-8");
                tcpToolServer.sendAddLen(pkg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
