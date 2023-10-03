package com.tutorial.apidemo.controller;
import com.tutorial.apidemo.models.Product;
import com.tutorial.apidemo.models.ResponseObject;
import com.tutorial.apidemo.respositories.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    @Autowired
    private ProductRespository respository;
    @GetMapping("")
    List<Product> getAllProduct(){
   return  respository.findAll();
}
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
       Optional<Product>foundProduct=respository.findById(id);
       if(foundProduct.isPresent()){
           return  ResponseEntity.status(HttpStatus.OK).body(
                   new ResponseObject("ok","Query product successfully",foundProduct)
           );
       }else {
           return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                   new ResponseObject("false","Cannot find product with id="+id,"")

           );
       }
    }
    @PostMapping(path = "/insert")
    ResponseEntity<ResponseObject> inserProduct(@RequestBody Product newProduct){
        List<Product> foundProducts = respository.findByProductName(newProduct.getProductName().trim());
        if(foundProducts.size()>0){
            return  ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed","Product name alredy taken","")
            );
        }
        return  ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Insert Product successfully",respository.save(newProduct))
        );
    }
   @DeleteMapping("/{id}")
   ResponseEntity<ResponseObject> deletelProduct(@PathVariable Long id){
      boolean exists=respository.existsById(id);
      if(exists){
          respository.deleteById(id);
          return  ResponseEntity.status(HttpStatus.OK).body(
                  new ResponseObject("oke","Deletel product successfully","")
          );
      }
      return  ResponseEntity.status(HttpStatus.OK).body(
              new ResponseObject("failse","Cannot find product to deletel","")
      );
  }
}
