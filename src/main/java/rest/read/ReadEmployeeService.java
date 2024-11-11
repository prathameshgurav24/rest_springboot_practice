package rest.read;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import rest.entity.Employee;
import rest.exception_aspect.NoSuchEmployeeFoundException;
import rest.read.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ReadEmployeeService {
    private static final Logger log = LoggerFactory.getLogger(ReadEmployeeService.class);

//-----setter injection with lombok---but best practice is traditional setter injection

    private ReadEmployeeEntityRepository readEmployeeEntityRepository;

    //-----constructor dependency injection
//    private ReadEmployeeService(ReadEmployeeEntityRepository readEmployeeEntityRepository) {
//
//        this.readEmployeeEntityRepository = readEmployeeEntityRepository;
//    }

//-----setter dependency injection
    @Autowired
    public void setReadEmployeeEntityRepository(ReadEmployeeEntityRepository readEmployeeEntityRepository){
        this.readEmployeeEntityRepository=readEmployeeEntityRepository;
    }


    @Async
    public CompletableFuture<Optional<List<Employee>>> getAllEmployees() {

        log.info("in getAllEmployee");
        return CompletableFuture.supplyAsync(()-> Optional.of(this.readEmployeeEntityRepository.findAll()));
    }

    @Async
    public CompletableFuture<EmployeeDTO> getEmployee(Long emp_id) {

        return  CompletableFuture.supplyAsync(()-> {
            Optional<Employee> employee = this.readEmployeeEntityRepository.findById(emp_id);

            if(employee.isEmpty()){
                throw new NoSuchEmployeeFoundException();
            }

            return new EmployeeDTO(employee.get().getEmp_id(),employee.get().getEmp_name(),employee.get().getSalary());
        });
    }

    @Async
    public CompletableFuture<EmployeeDTO> getEmployeeTest(Long emp_id) throws ExecutionException, InterruptedException {

        return  CompletableFuture.supplyAsync(()-> {
            Optional<Employee> employee = this.readEmployeeEntityRepository.findById(emp_id);

            if(employee.isEmpty()){
                throw new NoSuchEmployeeFoundException();
                }

            return new EmployeeDTO(employee.get().getEmp_id(),employee.get().getEmp_name(),employee.get().getSalary());
        });
    }


//    @Async
//    public CompletableFuture<Optional<Employee>> getEmployeesUsingRecords(Long id) {
//
//        return  CompletableFuture.supplyAsync(()-> Optional.of(this.readEmployeeEntityRepository.findById(id)));
//
//    }
}
