import org.remote.service.Remote;

public class Test {
    public static void main(String[] args) {
//        TestChannel testChannel = Remote.getInstance(TestChannel.class);
//        testChannel.method01(new String[]{});
//        System.out.println("========================================");
//        testChannel.method02(new String[]{});
//        System.out.println("========================================");
//        System.out.println("****************************************************************");

        TestChannel02 testChannel2 = Remote.getInstance(TestChannel02.class);
        testChannel2.method01(new String[]{});
        System.out.println("========================================");
        testChannel2.method02(new String[]{});
        System.out.println("========================================");

    }
}
