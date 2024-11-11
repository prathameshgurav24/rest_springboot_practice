package rest.read;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.entity.Employee;

@Repository
public interface ReadEmployeeEntityRepository extends JpaRepository<Employee,Long> {

//    @Query(value="SELECT emp_id,emp_name,salary FROM EMPLOYEE WHERE emp+id=?1")
//    Employee  findAllEmployees(@Param("emp_id") Long emp_id);
}
