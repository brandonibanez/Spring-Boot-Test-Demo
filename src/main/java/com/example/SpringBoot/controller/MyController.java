package com.example.SpringBoot.controller;

import com.example.SpringBoot.dao.CustomerDAO;
import com.example.SpringBoot.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MyController {

    private final CustomerDAO customerDAO;

    @GetMapping("/list-customers")
    public String listCustomers(Model model) {
        List<Customer> customerList = customerDAO.findAll();

        model.addAttribute("employees", customerList);

        return "list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Customer theCustomer = new Customer();

        theModel.addAttribute("employee", theCustomer);

        return "employee-form";
    }

    @RequestMapping("/customLoginPage")
    public String displayCustomLogin() {
        return "customLoginPage";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Customer theCustomer) {

        // save the customer
        customerDAO.save(theCustomer);

        // use a redirect to prevent duplicate submissions
        return "redirect:/list-customers";
    }

}
