package comsep23.textileindustry.controller;


import comsep23.textileindustry.config.Role;
import comsep23.textileindustry.entity.User;
import comsep23.textileindustry.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Textile Industry";
    }



    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.assignRole(user, user.getRole().toString());
        userService.addUser(user);

        if (user.getRole() == Role.ROLE_SALEMAN) {
            return "User registered as Salesman. Please proceed to the Sales System at /api/sale.";
        } else if (user.getRole() == Role.ROLE_DELIVERYMAN) {
            return "User registered as Deliveryman. Please proceed to the Delivery System at /api/delivery.";
        } else if (user.getRole() == Role.ROLE_PROVIDER) {
            return "User registered as Provider. Please proceed to the Supply System at /api/supply.";
        } else if (user.getRole() == Role.ROLE_CUSTOMER) {
            return "User registered as Customer. Please proceed to the Customer Service System at /api/customer.";
        }
        return "User registered with an undefined role.";
    }


}
