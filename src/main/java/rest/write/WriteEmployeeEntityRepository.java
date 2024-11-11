package rest.write;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.entity.Employee;

import java.util.Optional;

@Repository
public interface WriteEmployeeEntityRepository extends JpaRepository<Employee,Long> {

}
