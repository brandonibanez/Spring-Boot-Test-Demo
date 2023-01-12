package com.example.SpringBoot.controller;

import com.example.SpringBoot.dao.EmployeeDAO;
import com.example.SpringBoot.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MyController {

    private final EmployeeDAO employeeDAO;

    @GetMapping("/list-employees")
    public String listEmployees(Model model) {
        List<Employee> employeeList = employeeDAO.findAll();

        model.addAttribute("employees", employeeList);

        return "list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employee-form";
    }

    @RequestMapping("/customLoginPage")
    public String displayCustomLogin() {
        return "customLoginPage";
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee theEmployee,
                               BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()) {
            return "employee-form";
        }
        else {
            // save the customer
            employeeDAO.save(theEmployee);

            // use a redirect to prevent duplicate submissions
            return "redirect:/list-employees";
        }
    }
}
