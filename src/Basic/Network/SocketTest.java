package Basic.Network;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: Junlin Chen
 * @Date: 2021/05/30 11:58
 * @Describe:
 */
public class SocketTest {
    public static void main(String[] args) {
        // try 里面写语句会自动释放资源
        try(
                Socket socket=new Socket("time-a.nist.gov",13); // 获取时间研究所，提高的精度时间
                Scanner in =new Scanner(socket.getInputStream(),"UTF-8");
        ){
            while (in.hasNextLine()){
                System.out.println(in.nextLine());
            }
        }catch (IOException e){

        }
    }
}
