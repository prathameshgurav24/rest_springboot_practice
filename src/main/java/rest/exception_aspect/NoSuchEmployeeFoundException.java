package rest.exception_aspect;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoSuchEmployeeFoundException extends RuntimeException{

//    public NoSuchEmployeeFoundException(String employeeNotFound) {
//        super(employeeNotFound);
//    }
}
