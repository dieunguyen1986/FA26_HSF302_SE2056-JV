package employees.entities;

import org.springframework.stereotype.Component;

@Component
public class Employee {
    private Long empId;
    private String firstName;
    private String lastName;
    private double salary;
    private String gender;
    private String phoneNumber;
    private String address;

    public Employee() {

    }

    public Employee(Long empId, String firstName, String gender, String lastName, double salary, String address, String phoneNumber) {
        this.address = address;
        this.empId = empId;
        this.firstName = firstName;
        this.gender = gender;
        this.lastName = lastName;
        this.salary = salary;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
