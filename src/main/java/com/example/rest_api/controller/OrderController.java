package com.example.rest_api.controller;

import com.example.rest_api.model.Orders;
import com.example.rest_api.repository.OrderRepository;
import com.example.rest_api.repository.CustomerRepository;
import com.example.rest_api.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    // Menampilkan semua order yang ada
    @GetMapping("/orders")
    public String listOrders(Model model) {
        List<Orders> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "order_list"; // Mengarahkan ke halaman daftar order
    }

    // Menampilkan form untuk membuat order baru
    @GetMapping("/order/new")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new Orders()); // Inisialisasi objek order baru
        model.addAttribute("customers", customerRepository.findAll()); // Ambil semua customer dari repo
        model.addAttribute("products", productRepository.findAll()); // Ambil semua produk dari repo
        return "order_form"; // Mengarahkan ke halaman form order
    }

    // Menyimpan order baru atau yang diedit
    @PostMapping("/order/save")
    public String saveOrder(@ModelAttribute Orders order) {
        orderRepository.save(order); // Simpan order ke database
        return "redirect:/orders"; // Setelah disimpan, redirect ke halaman daftar order
    }
}
