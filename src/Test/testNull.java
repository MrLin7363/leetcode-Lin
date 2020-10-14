package Test;

/**
 * @author: Junlin Chen
 * @Date: 2020/09/26 16:41
 * @Describe:
 */
public class testNull {
    private String s;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public static void main(String[] args) {
        testNull t=null;
        /*if ("null".equals(t)) {
            System.out.println("no pro");
        }
        System.out.println("s");*/
        //会报错
       /* if (t.equals("null"))
            System.out.println("ye");*/
      if (null == t.getS())
            System.out.println("null");
        /*  if (t.getS()==null)
            System.out.println("yes");*/
    }
}
