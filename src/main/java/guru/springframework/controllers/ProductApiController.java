package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductApiController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    //Get all products in JSON format using REST API for mobile application
    @ResponseBody
    @RequestMapping(value = "api/product/all", method = RequestMethod.GET)
    public Iterable<Product> getAll() throws OutOfMemoryError {
        return productService.listAllProducts();
    }

    //Get Single product by Id value in JSON format using REST API for mobile application
    @ResponseBody
    @RequestMapping(value = "api/product/get", method = RequestMethod.GET)
    public Product getProduct(@RequestParam Integer id) {
        return productService.getProductById(id);
    }

    //Adding new product using REST API for mobile application
    @ResponseBody
    @RequestMapping(value = "api/product/add", method = RequestMethod.POST)
    public Boolean add(@RequestBody Product product) {
        Boolean success = false;
        Boolean exists = false;
        //check existing records
        for (Product p : productService.listAllProducts()) {
            if (p.getId() == product.getId()) {
                exists = true;
            }
        }

        if (!exists) {
            try {
                productService.saveProduct(product);
                success = true;

            } catch (Exception e) {
            }
        }
        return success;
    }

    // editing product using REST API for mobile application
    @ResponseBody
    @RequestMapping(value = "api/product/edit", method = RequestMethod.POST)
    public Boolean edit(@RequestBody Product product) {
        Boolean success = false;
        try {
            productService.saveProduct(product);
            success = true;

        } catch (Exception e) {
        }
        return success;
    }

    // Deleting product using REST API for mobile application
    @ResponseBody
    @RequestMapping(value = "api/product/delete", method = RequestMethod.DELETE)
    public Boolean deleteProduct(@RequestParam Integer id) {
        boolean deleted = false;
        try {
            Product p = productService.getProductById(id);
            productService.deleteProduct(p);
            deleted = true;
        } catch (Exception e) {
        }
        return deleted;
    }
}
