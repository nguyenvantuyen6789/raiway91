package com.data.controller;

import com.data.entity.Product;
import com.data.repository.ProductRepository;
import com.data.req.ProductCreateReq;
import com.data.req.ProductUpdateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    // khai bao repository
    // c1: @Autowired
    // c2: Constructor (duoc khuyen khich)
    // c3: Setter
//    @Autowired
//    private ProductRepository productRepo;

    private ProductRepository productRepo;
    public ProductController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

//    private ProductRepository productRepo;
//    @Autowired
//    public void setProductRepo(ProductRepository productRepo) {
//        this.productRepo = productRepo;
//    }

    @GetMapping("products")
    public ResponseEntity<?> getAll() {
        // findAll: lay toan bo du lieu cua entity
        List<Product> products = productRepo.findAll();
//        return new ResponseEntity<>("Get data success!", HttpStatus.OK);
        return ResponseEntity.ok(products);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        // find by id: lay ra 1 entity, neu id khong ton tai thi la null
        // Optional: dung de check du lieu null hay khong
        Optional<Product> opProduct = productRepo.findById(id);

        if (opProduct.isEmpty()) {
            return ResponseEntity.badRequest().body("Id khong ton tai: " + id);
        }

        Product product = opProduct.get();
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Optional<Product> opProduct = productRepo.findById(id);
        if (opProduct.isEmpty()) {
            return ResponseEntity.badRequest().body("Id khong ton tai: " + id);
        }

        productRepo.deleteById(id);
        return ResponseEntity.ok("Delete thanh cong id: " + id);
    }

    // request body: json: {"name": "milk tea", "price": 40000}
    // request param: localhost:8080?name=tea&price=1000

    @PostMapping("products")
    public ResponseEntity<?> create(@RequestBody ProductCreateReq productCreateReq) {
        if (productCreateReq.getName() == null) {
            return ResponseEntity.badRequest().body("Can phai dien name");
        } else if (productCreateReq.getPrice() == 0) {
            return ResponseEntity.badRequest().body("Gia tien phai lon hon 0");
        }

        Product product = new Product();
        product.setName(productCreateReq.getName());
        product.setPrice(productCreateReq.getPrice());
        product.setQuantity(productCreateReq.getQuantity());

        productRepo.save(product);

        return ResponseEntity.ok("Them san pham thanh cong: " + product.getName());
    }
    // chuc nang update: method PUT,
    // sv lam chuc nang update trong vong 15p
    @PutMapping("products/{id}")
    public ResponseEntity<?> update(@PathVariable int id,
                                    @RequestBody ProductUpdateReq productUpdateReq) {
        // validate name
        if (productUpdateReq.getName() == null) {
            return ResponseEntity.badRequest().body("Can phai dien name");
        } else if (productUpdateReq.getPrice() == 0) {
            // validate price
            return ResponseEntity.badRequest().body("Gia tien phai lon hon 0");
        }

        Product product = productRepo.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.badRequest().body("Id khong ton tai: " + id);
        }

        product.setName(productUpdateReq.getName());
        product.setPrice(productUpdateReq.getPrice());
        product.setQuantity(productUpdateReq.getQuantity());

        productRepo.save(product);

        return ResponseEntity.ok("Update thanh cong 4!");
    }

    // sv config auto reload: done
    // sv tao 1 project lam chuc nang crud employee: id, full name, address
    // salary
}
