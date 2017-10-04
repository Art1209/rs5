import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.integration.ClientAndProxy;
import org.mockserver.integration.ClientAndServer;

import static org.mockserver.integration.ClientAndProxy.startClientAndProxy;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

public class HelloServiceTester {

    private ClientAndProxy proxy;
    private ClientAndServer mockServer;

    @Before
    public void startProxy() {
        mockServer = startClientAndServer(1080);
        proxy = startClientAndProxy(1090);
    }

    @After
    public void stopProxy() {
        proxy.stop();
        mockServer.stop();
    }

    @Test
    public void TestHello(){}
}
