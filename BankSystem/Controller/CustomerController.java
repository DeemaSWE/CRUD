package com.example.crud_exercise.BankSystem.Controller;

import com.example.crud_exercise.Api.ApiResponse;
import com.example.crud_exercise.BankSystem.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    ArrayList<Customer> customers = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customer> getCustomers(){
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customer customer){
        customers.add(customer);
        return new ApiResponse("Customer added");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customer customer){
        try {
            customers.set(index, customer);
            return new ApiResponse("Customer updated");
        } catch (IndexOutOfBoundsException e){
            return new ApiResponse("Index out of bound");
        }
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index){
        try {
            customers.remove(index);
            return new ApiResponse("Customer deleted");
        }catch (IndexOutOfBoundsException e){
            return new ApiResponse("Index out of bound");
        }
    }

    @PutMapping("/deposit/{index}/{amount}")
    public ApiResponse deposit(@PathVariable int index, @PathVariable double amount){
        try {
            Customer customer = customers.get(index);
            customer.setBalance(customer.getBalance() + amount);
            return new ApiResponse("Money deposited successfully");
        } catch (IndexOutOfBoundsException e){
            return new ApiResponse("Index out of bound");
        }
    }

    @PutMapping("/withdraw/{index}/{amount}")
    public ApiResponse withdraw(@PathVariable int index, @PathVariable double amount){
        try {
            Customer customer = customers.get(index);
            if(amount <= customer.getBalance()){
                customer.setBalance(customer.getBalance() - amount);
                return new ApiResponse("Money withdrawn successfully");
            }
            return new ApiResponse("Insufficient balance");
        } catch (IndexOutOfBoundsException e){
            return new ApiResponse("Index out of bound");
        }
    }

}
