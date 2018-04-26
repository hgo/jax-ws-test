package app;

import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void doTest() throws Exception {
        Endpoint endpoint = Endpoint.publish("http://localhost:8080/calculator", new CalculatorImpl());
        assertTrue(endpoint.isPublished());
        assertEquals("http://schemas.xmlsoap.org/wsdl/soap/http", endpoint.getBinding().getBindingID());

        URL wsdlDocumentLocation = new URL("http://localhost:8080/calculator?wsdl");
        String namespaceURI = "http://app/";
        String servicePart = "CalculatorImplService";
        String portName = "CalculatorImplPort";
        QName serviceQN = new QName(namespaceURI, servicePart);
        QName portQN = new QName(namespaceURI, portName);

        Service service = Service.create(wsdlDocumentLocation, serviceQN);
        logWsdl(wsdlDocumentLocation.openConnection());
        Calculator calculator = service.getPort(portQN, Calculator.class);
        AddOut add = calculator.add(1, 2);
        System.out.println(add);

        assertEquals(3, add.getValue());
        endpoint.stop();
        assertFalse(endpoint.isPublished());
    }

    private void logWsdl(URLConnection urlConnection) throws IOException {
        InputStream is = urlConnection.getInputStream();

        int size = 0;
        byte[] buffer = new byte[1024];
        System.err.println("~WSDL~~~~~~~~~~~~~~~~~~~~~~~");
        System.err.println("\n\n");
        while ((size = is.read(buffer)) != -1) System.err.write(buffer, 0, size);
        System.err.println("\n\n`");
        System.err.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }


}