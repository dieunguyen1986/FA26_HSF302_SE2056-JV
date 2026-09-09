package employees.services.impl;

import employees.dao.EmployeeDao;
import employees.dao.impl.EmployeeDaoImpl;
import employees.entities.Employee;
import employees.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeService") // Đã được quản lý và khởi tạo bởi IoC Container (ApplicationContext)
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    // DI: Dependency Injection
    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public boolean save(Employee employee) {
        // Business logic

        if (employeeDao.existPhoneNumber(employee.getPhoneNumber())) {
            throw new RuntimeException("Phone number already exist");
        }

        return employeeDao.save(employee);
    }
}
