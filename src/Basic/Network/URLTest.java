package Basic.Network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * @author: Junlin Chen
 * @Date: 2021/05/30 14:09
 * @Describe:
 */
public class URLTest {
    public static void main(String[] args) throws IOException {
        /*URL url=new URL("http://www.baidu.com");
        InputStream inputStream=url.openStream();
        Scanner in =new Scanner(inputStream,"UTF-8");
        connection.getHeaderFields().forEach((k,v)-> System.out.println("k="+k+"  v="+v));
        while (in.hasNextLine()){
            System.out.println(in.nextLine()); // 输出百度的 html 信息
        }*/

        /*
        这里能连接上 telnettest 服务，但是out流写没有接收到
         */
        URL url=new URL("http://127.0.0.1:8080");
        // 使用 URLConnection 获取信息
        URLConnection connection= url.openConnection();
        connection.setDoOutput(true); // 建立可输出的连接
//        connection.connect();
        PrintWriter out = new PrintWriter(connection.getOutputStream());
        out.print("URLTest客户端接收到数据");
        out.write("asdasd");
        Scanner in = new Scanner(connection.getInputStream(), "UTF-8");
        while (in.hasNextLine()){
            System.out.println(in.nextLine());
        }

        out.close();
    }
}
