package InterviewTopic;

/**
 * @author: Junlin Chen
 * @Date: 2021/03/21 13:55
 * @Describe:
 */
public class tryca {
    public static void main(String[] args) {
        System.out.println(test());
    }

    public static int test(){
        try{
            return 1;
        }catch(Exception e){

        }finally {
            return 2;
        }
    }
}
