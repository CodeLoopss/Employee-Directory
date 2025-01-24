package com.luv2code.springboot.cruddemo.rest;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // expose "/employees" and return a list of employees
    @GetMapping("/list")
    public String findAll(Model model) {

        List<Employee> listemployees=employeeService.findAll();
        System.out.println(listemployees);
        model.addAttribute("employees",listemployees);
        return "getemployee";
    }
@GetMapping("/showformadd")
    public String showformadd(Model model){
        Employee theemployee=new Employee();

        model.addAttribute("employee",theemployee);
        return "addemployee";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
    //save the employee
        employeeService.save(employee);
        //use a redirect to prevent duplicate submission
        return "redirect:/employee/list";
    }
    @GetMapping("/showformUpdate")
    public String showUpdateform(@RequestParam("employeeId") int Id,Model model){
        //get the employee from service

        Employee theemployee=employeeService.findById(Id);
        //set the employee in the model to prepopulate the form
        model.addAttribute("employee",theemployee);

        return "addemployee";
    }
    @GetMapping("/deleteEmp")
    public String deleteEmployee(@RequestParam("employeeId")int Id ) {
        //delete the employee by id
        employeeService.deleteById(Id);
        //redirect to emp page
        return "redirect:/employee/list";
    }

    // add mapping for POST /employees - add new employee


}















