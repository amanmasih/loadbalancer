package com.niit.userproductservice.controller;

import com.niit.userproductservice.exception.CustomerAlreadyExist;
import com.niit.userproductservice.exception.ProductAlreadyExist;
import com.niit.userproductservice.exception.CustomerNotFound;
import com.niit.userproductservice.model.Customer;
import com.niit.userproductservice.model.Product;
import com.niit.userproductservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@RequestMapping("api/v1")
public class ProductController {
    ProductService productService;

    @Autowired
    @LoadBalanced
    private RestTemplate rest;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;

    }

    @PostMapping("/register")
    public ResponseEntity<?> saveProductDetails(@RequestBody Customer customer) throws RestClientException,IOException {
        try {
                ResponseEntity<?> response =null;
                response=rest.exchange("http://USERAUTHSERVICE-PRODUCER/register", HttpMethod.POST, getHeaders(),String.class);

                System.out.println(response.getBody());
                System.out.println(response.getHeaders());

                return response;

            // A GET request will be performed to the given URL sending the HTTP Headers that are wrapped in the HttpEntity instances
            // here responseEntity will be returned as a String and wrapped into ResponseEntity instance
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // HttpEntity is a helper object which contains header+ body of an Http request or response
    // it is used to return the response.
    // HttpEntity is parent . RequestEntity extends HttpEntity
    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }


//    @PostMapping("/customerData/saveproduct/{customerId}")
//    public ResponseEntity<?> saveProductDetails(@RequestBody Product product,@PathVariable String customerId) throws CustomerNotFound {
//        try {
//            return  new ResponseEntity<>(productService.saveCustomerProduct(customerId,product), HttpStatus.CREATED);
//        }
//        catch(CustomerNotFound e)
//        {
//            throw new CustomerNotFound();
//        }
//        catch(Exception e)
//        {
//            return new ResponseEntity<>("try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @GetMapping("/customerData/getproduct")
//    public ResponseEntity<?> getAllProduct(){
//        try{
//            return new ResponseEntity<>(productService.getAllProductsOfACustomer(),HttpStatus.OK);
//        }
//        catch(Exception e)
//        {
//            return  new ResponseEntity<>("try after some time",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @DeleteMapping("/customerData/product/{customerId}/{productCode}")
//    public ResponseEntity<?> deleteCustomer(@PathVariable String customerId,@PathVariable int productCode)throws CustomerNotFound
//    {
//        try{
//            return new ResponseEntity<>(productService.deleteProductOfACustomer(customerId,productCode),HttpStatus.OK);
//        }
//        catch (CustomerNotFound e){
//            throw new CustomerNotFound();
//        }
//        catch(Exception e){
//            return new ResponseEntity<>("try after some time",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}//end of the class
