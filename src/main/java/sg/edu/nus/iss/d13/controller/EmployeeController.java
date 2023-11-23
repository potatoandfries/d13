package sg.edu.nus.iss.d13.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.d13.model.Employee;
import sg.edu.nus.iss.d13.repo.EmployeeRepo;

@Controller
@RequestMapping(path = "/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepo empRepo;// what does autowiring do?
                         // public class EmployeeController{
                         // empRepo = new EmployeeRepo()
                         // }

    @GetMapping("/list")
    public String employeeList(Model model) {
        List<Employee> employees = empRepo.findAll();

        model.addAttribute("employees", employees);
        return "employeelist";
    }

    @GetMapping("/addnew")
    public String employeeAdd(Model model) {
        Employee emp = new Employee();
        model.addAttribute("employee", emp);

        return "employeeadd";
    }

    // this is the one i changed, it allows for the thing to be parsed in
    @PostMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employeeForm, BindingResult result,
            Model model) throws FileNotFoundException {

        if (result.hasErrors()) {
            return "employeeadd";
        }

        Boolean returnResult = empRepo.save(employeeForm);

        // return "redirect:/employees/list";
        model.addAttribute("savedEmployee", employeeForm);
        return "success";
    }

    @GetMapping("/employeedelete/{email}")
    public String deleteEmployee(@PathVariable("email") String email) {
        Employee emp = empRepo.findbyEmail(email);

        Boolean result = empRepo.delete(emp);

        return "redirect:/employees/list";

    }

    @GetMapping("/employeeupdate/{email}")
    public String updateEmployee(@PathVariable("email") String email, Model model) {
        Employee emp = empRepo.findbyEmail(email);
        model.addAttribute("employee", emp);

        return "employeeupdate";
    }

    @PostMapping("/updEmployee")
    public String updateEmployeeRecord(@ModelAttribute("employee") Employee emp, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "employeeupdate";

        } else {
            empRepo.updateEmployee(emp);
            return "redirect:/employees/list";
        }

    }
}
