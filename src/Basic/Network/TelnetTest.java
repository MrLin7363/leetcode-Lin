package Basic.Network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: Junlin Chen
 * @Date: 2021/05/30 13:02
 * @Describe:
 */
public class TelnetTest {
    public static void main(String[] args) throws IOException {
        // 在本地建立 127.0.0.1 8080 的服务  然后等待 telnet 客户端连接 三次握手
        // 也可以为每一个连接生成一个单独的线程去跑
        try (ServerSocket s = new ServerSocket(8080)) {
            try (Socket incoming = s.accept()) {
                InputStream inStream = incoming.getInputStream();
                OutputStream outStream = incoming.getOutputStream();
                try (Scanner in = new Scanner(inStream, "UTF-8")) {
                    PrintWriter writer = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"));
                    writer.println("Hello! junlin Enter BYE to exit.");
                    boolean done = false;
                    while (!done && in.hasNextLine()) {
                        String line = in.nextLine();
                        writer.println("服务器:" + line);
                        System.out.println("接收到客户端信息:" + line);
                        if (line.trim().equals("BYE")) {
                            done = true;
                        }
                    }
                }
            }
        }
    }


}
