package employees.dao;

import employees.entities.Employee;

public interface EmployeeDao {
    boolean save(Employee employeee);

    boolean existPhoneNumber(String number);
}
