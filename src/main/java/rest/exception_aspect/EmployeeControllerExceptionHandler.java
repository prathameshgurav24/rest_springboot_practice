package rest.exception_aspect;

import io.jsonwebtoken.ExpiredJwtException;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Aspect
@RestControllerAdvice
public class EmployeeControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value=NoSuchEmployeeFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException() {

        return new ResponseEntity<>("Employee Not Found", HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(value= ExpiredJwtException.class)
    public ResponseEntity<Object> handleJwtTokenExpiredException() {

        return new ResponseEntity<>("JWT token expired", HttpStatus.UNAUTHORIZED);
    }
}
