import org.remote.annotation.RemoteChannel;
import org.remote.annotation.RemoteServiceCode;

@RemoteChannel("core")
public interface TestChannel {

    @RemoteServiceCode("s005001")
    void method01(String[] args);

    void method02(String[] args);
}
