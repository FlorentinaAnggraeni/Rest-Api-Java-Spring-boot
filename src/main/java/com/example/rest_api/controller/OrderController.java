import com.example.rest_api.model.Orders;
import com.example.rest_api.repository.OrderRepository;
import com.example.rest_api.repository.CustomerRepository;
import com.example.rest_api.repository.ProductRepository;


import
org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import
org.springframework.web.bind.annotation.GetMapping;
import
org.springframework.web.bind.annotation.PostMapping;
import
org.springframework.web.bind.annotation.ModelAttribute;
import java.util.List;

@Controller
public class OrderController {
 @Autowired
 private OrderRepository orderRepository;
 @Autowired
 private CustomerRepository customerRepository;
 @Autowired
 private ProductRepository productRepository;
 @GetMapping("/orders")
 public String listOrders(Model model) {
 List<Orders> orders = orderRepository.findAll();
 model.addAttribute("orders", orders);
 return "order_list";
 }
 @GetMapping("/order/new")
 public String showOrderForm(Model model) {
 model.addAttribute("order", new Orders());
 model.addAttribute("customers",
customerRepository.findAll());
 model.addAttribute("products",
productRepository.findAll());
 return "order_form";
 }
 @PostMapping("/order/save")
 public String saveOrder(@ModelAttribute Orders order)
{
 orderRepository.save(order);
 return "redirect:/orders";
 }
}
