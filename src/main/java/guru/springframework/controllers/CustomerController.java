package guru.springframework.controllers;


import guru.springframework.repositories.CustomerRepository;
import guru.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerRepository(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String list(Model model) throws OutOfMemoryError {
        try {
            model.addAttribute("customers",customerService.listAllGuests());
            System.out.println("Returning products:");
        } catch (OutOfMemoryError e) {
            throw new OutOfMemoryError("Memory ran out while loading products");
        }
        return "customers";
    }
}
