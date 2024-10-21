package com.example.rest_api;

import com.example.rest_api.model.Customer;
import com.example.rest_api.model.Product;
import com.example.rest_api.model.Orders;
import com.example.rest_api.repository.CustomerRepository;
import com.example.rest_api.repository.ProductRepository;
import com.example.rest_api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("mvc")
public class SpringMvcApplication implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Product product1 = new Product("Laptop", "High-end gaming laptop", 1200.0);
        Product product2 = new Product("Smartphone", "Latest 5G smartphone", 800.0);
        productRepository.saveAll(Arrays.asList(product1, product2));

        Customer customer1 = new Customer("John Doe", "john@example.com");
        Customer customer2 = new Customer("Jane Smith", "jane@example.com");
        customerRepository.saveAll(Arrays.asList(customer1, customer2));

        Orders order1 = new Orders(2, customer1, product1);
        Orders order2 = new Orders(1, customer2, product2);
        orderRepository.saveAll(Arrays.asList(order1, order2));
    }
}
