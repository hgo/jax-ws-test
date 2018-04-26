package app;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@WebService(endpointInterface = "app.Calculator")
public class CalculatorImpl implements Calculator {

    @Resource
    WebServiceContext context;

    public AddOut add(int a, int b) {
        System.err.println("context: " + context);
        System.err.println("messageContext: " + context.getMessageContext());
        System.err.println("userPrincipal: " + context.getUserPrincipal());
        AddOut addOut = new AddOut(a + b);
        System.out.println(addOut);
        return addOut;
    }
}