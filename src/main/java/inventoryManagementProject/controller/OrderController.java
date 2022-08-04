package inventoryManagementProject.controller;

import inventoryManagementProject.entity.Customer;
import inventoryManagementProject.entity.Employee;
import inventoryManagementProject.entity.Order;
import inventoryManagementProject.entity.Product;
import inventoryManagementProject.service.CustomerService;
import inventoryManagementProject.service.EmployeeService;
import inventoryManagementProject.service.OrderService;
import inventoryManagementProject.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    private OrderService orderService;
    private CustomerService customerService;
    private EmployeeService employeeService;
    private ProductService productService;


    public OrderController(OrderService orderService, CustomerService customerService, EmployeeService employeeService, ProductService productService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.productService = productService;
    }

    // handler method to handle list orders and return mode and view
        @GetMapping("/orders")
        public String listOrders(Model model) {
            model.addAttribute("orders", orderService.getAllOrders());
            return "orders";
        }

        @GetMapping("/orders/new")
        public String createOrderForm(Model model) {
            // create order object to hold order form data
            Order order = new Order();
            model.addAttribute("order", order);

            List<Customer> allCustomers = customerService.getAllCustomers();
            model.addAttribute("customers", allCustomers);

            List<Employee> allEmployees = employeeService.getAllEmployees();
            model.addAttribute("employees", allEmployees);

            List<Product> allProducts = productService.getAllProducts();
            model.addAttribute("products", allProducts);

            return "create_order";
        }

        @PostMapping("/orders")
        public String saveOrder(@ModelAttribute("order") Order order) {
            //pavaliduoti produkta prie ji saugant - sukuri nauja beansa-order validation
            // service ir jis galetu tureti metoda validate order, arba/ir paziureti validacijas Thymeleafe...
            orderService.saveOrder(order);
            return "redirect:/orders";
        }

        @GetMapping("/orders/edit/{id}")
        public String editOrderForm(@PathVariable(value = "id") Long orderId, Model model) {
            // kitame projekte buvo panaudota "...@PathVariable(value = "id") long partNumberId..."
            // ,nes kuriant aprasyma padaryta "private long partNumberId;" o cia "private Long partNumberId;";

            model.addAttribute("order", orderService.getOrderById(orderId));
            return "edit_order";

        }

        @PostMapping("/orders/{id}")
        public String updateOrder(@PathVariable(value = "id") Long orderId,
                @ModelAttribute("order") Order order,
                Model model) {
            // get order from database by id
            Order existingOrder = orderService.getOrderById(orderId);
            // iskelti iki 68 eilute i order service nauja metoda
            existingOrder.setOrderId(orderId);
            existingOrder.setOrderDate(order.getOrderDate());

            // save updated order object
            orderService.updateOrder(existingOrder);
            return "redirect:/orders";
        }

        // handler method to handle delete order request

        @GetMapping("/orders/{id}")
        public String deleteOrder(@PathVariable(value = "id") Long orderId) {
            orderService.deleteOrderById(orderId);
            return "redirect:/orders";
        }
    }


