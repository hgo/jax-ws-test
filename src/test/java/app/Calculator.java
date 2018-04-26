package app;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface Calculator {
    @WebMethod
    AddOut add(@WebParam(header = true) int a, int b);
}