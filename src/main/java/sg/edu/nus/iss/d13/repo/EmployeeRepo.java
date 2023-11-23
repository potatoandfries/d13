package sg.edu.nus.iss.d13.repo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.d13.model.Employee;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;

@Repository
public class EmployeeRepo {

    final String dirPath = "C:\\Data";

    final String fileName = "employee.txt";

    private List<Employee> employees;

    public EmployeeRepo() throws ParseException {

        if (employees == null) {
            employees = new ArrayList<Employee>();
        }
        // import java.text.DateFormat;
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

        Date dt = df.parse("1965-08-09");
        Employee emp = new Employee("Hsien Loong", "Lee", "leehsienloong@gov.sg", 91234567, 8500, dt, 272210);
        employees.add(emp);

        emp = new Employee("Pritam", "Singh", "pritam@wp.sg", 98473000, 7500, dt, 272210);
        employees.add(emp);

    }

    public List<Employee> findAll() {
        return employees;
    }

    public Boolean delete(Employee employee) {

        Boolean result = false;

        int employeeIndex = employees.indexOf(employee);

        if (employeeIndex >= 0) {
            employees.remove(employeeIndex);
            result = true;
        }
        return result;
    }

    public Employee findbyEmail(String email) {
        return employees.stream().filter(emp -> emp.getEmail().equals(email)).findFirst().get();
    }

    public boolean save(Employee employee) throws FileNotFoundException {

        Boolean result = false;
        result = employees.add(employee);

        File f = new File(dirPath + "/" + fileName);
        OutputStream os = new FileOutputStream(f, true);
        PrintWriter pw = new PrintWriter(os);
        pw.println(employee.toString());
        pw.flush();
        pw.close();

        return result;

    }

    public Boolean updateEmployee(Employee employee) {

        Boolean result = false;

        Employee empObj = employees.stream().filter(emp -> emp.getEmail().equals(employee.getEmail())).findFirst()
                .get();

        int employeeIndex = employees.indexOf(empObj);

        if (employeeIndex >= 0) {
            // perform the update
            employees.get(employeeIndex).setBirthday(employee.getBirthday());
            employees.get(employeeIndex).setEmail(employee.getEmail());
            employees.get(employeeIndex).setFirstName(employee.getFirstName());
            employees.get(employeeIndex).setLastName(employee.getLastName());
            employees.get(employeeIndex).setPhoneNo(employee.getPhoneNo());
            employees.get(employeeIndex).setPostalCode(employee.getPostalCode());
            employees.get(employeeIndex).setSalary(employee.getSalary());
            result = true;

        }
        return result;

    }

}