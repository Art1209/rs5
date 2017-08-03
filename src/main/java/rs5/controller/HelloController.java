package rs5.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import rs5.persistence.entity.Role;
import rs5.persistence.entity.User;
import rs5.persistence.service.UserService;

@Log4j
@Controller
@RequestMapping("/Hello")
public class HelloController {

    @Autowired
    UserService us;

    @Autowired
    PasswordEncoder encoder;


    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public String helloUser(Model model, @PathVariable(value = "user", required = false)String user){
        model.addAttribute("username", user==null?"unknown":user);
//        User tempuser  = new User("admin",encoder.encode("password"));
//        Role tempRole = new Role("ROLE_ADMIN");
//        tempRole.getUsers().add(tempuser);
//        tempuser.getRolesList().add(tempRole);
//        us.addUser(tempuser);

        return "hello";
    }

}
