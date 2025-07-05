import com.example.remote.annotation.RemoteChannel;
import com.example.remote.annotation.RemoteServiceCode;

//@RemoteChannel("testChannel001")
public interface TestChannel {

    @RemoteServiceCode("request01")
    void method01(String[] args);

    void method02(String[] args);
}
