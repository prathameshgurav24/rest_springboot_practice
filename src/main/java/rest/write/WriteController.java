package rest.write;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import rest.entity.Employee;

import java.util.concurrent.CompletableFuture;

@Setter
@RestController
@CrossOrigin
@EnableWebSecurity
@RequestMapping("/employee/write")
public class WriteController {

    @Autowired
    private WriteEmployeeService writeEmployeeService;

    @PostMapping("/hello")
    public String getString(){
        return "Hello";
    }

    @PostMapping("/one")
    public CompletableFuture<ResponseEntity<Employee>> createEmployee(@RequestBody Employee employee) {

        System.out.println("in post");
        return writeEmployeeService.postEmployees(employee)
                .thenApply(ResponseEntity::ok)
                .exceptionally(ex -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }


}
