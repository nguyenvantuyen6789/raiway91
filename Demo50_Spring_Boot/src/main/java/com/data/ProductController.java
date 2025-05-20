package com.data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Class Java + @RestController = Controller: Api
// Class Java + @Controller = Controller: Kết hợp html css
@RestController
public class ProductController {

    // String: Trả về chuỗi
    // int: Trả về số
    // boolean: Trả về true/false
    @GetMapping("getData")
    public String getData1() {
        return "Nguyễn Văn B";
    }
    @GetMapping("getData2")
    public int getData2() {
        return 12345;
    }
    @GetMapping("getData3")
    public boolean getData3() {
        return true;
    }
    // Product: Product
    // List<Product>
    @GetMapping("getData4")
    public Product getData4() {
        Product product = new Product();
        product.setId(1);
        product.setProductName("Car");

        return product;
    }
    @GetMapping("getData5")
    public List<Product> getData5() {
        Product product2 = new Product();
        product2.setId(2);
        product2.setProductName("Car");
        Product product3 = new Product();
        product3.setId(3);
        product3.setProductName("Hat");

        List<Product> products = List.of(product2, product3);

        return products;
    }

    // Spring ho tro kieu tra ve da nang cho tat ca kieu du lieu
    // ResponseEntity<?>: String, int, boolean, Product, List...
}
