package employees.controller;

import config.AppConfig;
import employees.entities.Employee;
import employees.services.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EmployeeController {

    public static void main(String[] args) {
        // Lấy đối tượng được quản lý bởi IoC
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("employees");
        EmployeeService employeeService = (EmployeeService) applicationContext.getBean("employeeService");

        ApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(AppConfig.class);
        // Singleton design pattern
        Employee employee1 = applicationContext1.getBean("employee", Employee.class);
        Employee employee2 = applicationContext1.getBean("employee", Employee.class);


        // Prototype
        Employee employee3 = new Employee(1L, "Dat", "Male", "Bui",
                1000, "Hanoi", "0123456788");
        Employee employee4 = new Employee(1L, "Dat", "Male", "Bui",
                1000, "Hanoi", "0123456788");

        employee1.setSalary(1200);

        System.out.println("Emp2: " + employee2.getSalary());

        employee3.setSalary(1200);
        System.out.println("Emp4: "+ employee4.getSalary());
        try {
            employeeService.save(employee3);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
