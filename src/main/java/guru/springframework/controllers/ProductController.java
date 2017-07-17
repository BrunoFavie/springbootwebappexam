package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

//Get all products for website
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model) throws OutOfMemoryError{
        try {
            model.addAttribute("products", productService.listAllProducts());
            System.out.println("Returning products:");
        }
        catch(OutOfMemoryError e){
            throw new OutOfMemoryError("Memory ran out while loading products");
        }
        return "products";
    }

    //Get all products in JSON format using REST API for mobile application
    @ResponseBody
    @RequestMapping(value = "api/product/all", method = RequestMethod.GET)
    public Iterable<Product> getAll() throws OutOfMemoryError {
        return productService.listAllProducts();
    }

    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "productshow";
    }

    //Get Single product by Id value in JSON format using REST API for mobile application
    @ResponseBody
    @RequestMapping(value = "api/product/get", method = RequestMethod.GET)
    public Product getProduct(@RequestParam Integer id) {
        return productService.getProductById(id);
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "productform";
    }

    //Adding new product using website
    @RequestMapping("product/new")
    public String newProduct(Model model){
        model.addAttribute("product", new Product());
        return "productform";
    }

    //Adding new product using REST API for mobile application
    @ResponseBody
    @RequestMapping(value = "api/product/add", method = RequestMethod.POST)
    public Boolean add(@RequestBody Product product){
        Boolean success = false;
        try {
            productService.saveProduct(product);
            success = true;

        }catch(Exception e){
        }
        return success;
    }

    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product){

        productService.saveProduct(product);

        return "redirect:/product/" + product.getId();
    }

}
