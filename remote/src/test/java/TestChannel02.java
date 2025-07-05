import com.example.remote.annotation.RemoteChannel;
import com.example.remote.annotation.RemoteServiceCode;

@RemoteChannel("testChannel002")
public interface TestChannel02 {

    void method01(String[] args);

    void method02(String[] args);
}
