package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    //Get all products for website
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String list(Model model) throws OutOfMemoryError {
        try {
            model.addAttribute("products", productService.listAllProducts());
            System.out.println("Returning products:");
        } catch (OutOfMemoryError e) {
            throw new OutOfMemoryError("Memory ran out while loading products");
        }
        return "products";
    }

    @RequestMapping("product/{id}")
    public String showProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productshow";
    }

    // editing product using webapplication
    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "productform";
    }


    //Adding new product using website
    @RequestMapping("product/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "productform";
    }


    @RequestMapping(value = "product", method = RequestMethod.POST)
    public String saveProduct(Product product) {

        productService.saveProduct(product);

        return "redirect:/product/" + product.getId();
    }

    }
