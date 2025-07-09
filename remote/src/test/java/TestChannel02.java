import org.remote.annotation.RemoteChannel;

@RemoteChannel("testChannel002")
public interface TestChannel02 {

    void method01(String[] args);

    void method02(String[] args);
}
