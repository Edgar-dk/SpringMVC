package com.sias.mvc.Restful;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

/**
 * @author Edgar
 * @create 2022-01-25 15:41
 */

@Controller
public class EmployeeController {

    @Autowired//意思是把这个类注入进到这个类中，自动装配,也是可以在方法的参数中写的
    private EmployeeDao employeeDao;

    @RequestMapping(value = "/employee",method = RequestMethod.GET)
    public String getAllEmployee(Model model){
        Collection<Employee> employeeList = employeeDao.getAll();
        model.addAttribute("employeeList1",employeeList);
        return "employee-list";
    }

    @RequestMapping(value = "/employee/{id}",method = RequestMethod.DELETE)
    public String delteemployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee" ,method = RequestMethod.POST)
    public String addEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/employee";
    }
    @RequestMapping(value = "/employee/{id}",method = RequestMethod.GET)
    public String getElementById(@PathVariable("id") Integer id ,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("Employee",employee);
        return "employee-update";
    }
    @RequestMapping(value = "/employee",method = RequestMethod.PUT)
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/employee";
    }



//    @RequestMapping(value = "/employee",method = RequestMethod.GET)
//    public ModelAndView getAllEmployee(){
//        Collection<Employee> employeeList = employeeDao.getAll();
//        ModelAndView mav =new ModelAndView();
//        mav.addObject("employeeList",employeeList);
//        mav.setViewName("employee_list");
//        return mav;
//    }


//    @RequestMapping(value = "employee")
//    public String employee(){
//        return "success";
//    }
//
}
