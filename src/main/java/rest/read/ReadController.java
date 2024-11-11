package rest.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import rest.security.AuthRequest;
import rest.entity.Employee;
import rest.read.dto.EmployeeDTO;
import rest.security.JWTservice;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/employee/read")
public class ReadController {


    private ReadEmployeeService readEmployeeService;
    private JWTservice jwTservice;

    private AuthenticationConfiguration authConfiguration;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJWTservice(JWTservice jwTservice) {
        this.jwTservice = jwTservice;
    }

    @Autowired
    public void setReadEmployeeService(ReadEmployeeService readEmployeeService) {
        this.readEmployeeService = readEmployeeService;
    }

    @PostMapping("/hello")
    public String getString() {
        return "Hello";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CompletableFuture<Optional<List<Employee>>> getAllEmployee() {

        return readEmployeeService.getAllEmployees();
    }


    @GetMapping("/byid/{id}")
    public ResponseEntity<EmployeeDTO> getById(@PathVariable Long id) throws ExecutionException, InterruptedException {

        return ResponseEntity.ok(readEmployeeService.getEmployee(id).get());
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<EmployeeDTO> test(@PathVariable Long id) throws ExecutionException, InterruptedException {

        return ResponseEntity.ok(readEmployeeService.getEmployeeTest(id).get());

    }

    @PostMapping("/authenticate")
    public String authenrticatedAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwTservice.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
