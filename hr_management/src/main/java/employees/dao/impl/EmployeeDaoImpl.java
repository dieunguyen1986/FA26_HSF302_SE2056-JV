package employees.dao.impl;

import employees.dao.EmployeeDao;
import employees.entities.Employee;
import org.springframework.stereotype.Repository;

@Repository("employeeDao") // Đã được quản lý và khởi tạo bởi IoC Container (ApplicationContext)
// Spring Bean
public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(Employee employeee) {
        // Connect to DB  insert into
        System.out.println("Employee saved");

        return true;
    }

    @Override
    public boolean existPhoneNumber(String number) {
        if ("0123456789".equals(number)) {
            return  true;
        }
        return false;
    }
}
