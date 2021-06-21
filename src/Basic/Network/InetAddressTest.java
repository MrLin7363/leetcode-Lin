package Basic.Network;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;

/**
 * @author: Junlin Chen
 * @Date: 2021/05/30 12:14
 * @Describe:
 */
public class InetAddressTest {
    public static void main(String[] args) throws IOException {
        InetAddress inetAddress=InetAddress.getByName("time-a.nist.gov");
        byte[] addressBytes = inetAddress.getAddress();
        System.out.println(addressBytes);

        // google
        InetAddress[] inetAddresses = InetAddress.getAllByName("www.google.com");
        Arrays.stream(inetAddresses).forEach(e-> System.out.println(e));

        // 本机地址
        InetAddress localAddress= InetAddress.getLocalHost();
        System.out.println(localAddress);


        if (args.length>0){
            String host="time-a.nist.gov";
            InetAddress[] addresses=InetAddress.getAllByName(host);
            for (InetAddress a:addresses){
                System.out.println(a);
            }
        }
    }
}
