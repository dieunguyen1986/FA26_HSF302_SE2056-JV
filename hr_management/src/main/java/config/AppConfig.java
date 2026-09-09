package config;

import employees.entities.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // Config bean: ththiết lập cách thức mà IoC Container tạo bean

    @Bean
    public Employee employee() {
        Employee employee = new Employee(2L, "Hieu", "Male", "Bui",
                1000, "Hanoi", "0987654356");

        return employee;
    }

}
