package rest.write;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.entity.Employee;

import java.util.concurrent.CompletableFuture;

@Service
public class WriteEmployeeService {

//-----setter injection with lombok---but best practice is traditional setter injection
    @Autowired
    @Setter
    private WriteEmployeeEntityRepository writeEmployeeEntityRepository;

    public CompletableFuture<Employee> postEmployees( Employee employee) {

        return CompletableFuture.supplyAsync(() -> {
            // Process the employee data and save it to the database
            return writeEmployeeEntityRepository.save(employee);
        });
    }

}
