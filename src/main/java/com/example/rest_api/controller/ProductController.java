package com.example.rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import com.example.rest_api.model.Product;
import com.example.rest_api.repository.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Menampilkan semua produk
    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product_list";
    }

    // Menampilkan form untuk produk baru
    @GetMapping("/product/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    // Menampilkan form untuk edit produk
    @GetMapping("/product/edit/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "product_form";
    }

    // Menyimpan produk baru atau hasil edit
    @PostMapping("/product/save")
    public String saveProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    // Menghapus produk berdasarkan id
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        productRepository.delete(product);
        return "redirect:/products";
    }
}
