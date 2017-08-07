package rs5.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rs5.persistence.entity.Role;
import rs5.persistence.entity.User;
import rs5.persistence.service.RoleService;
import rs5.persistence.service.UserService;

import java.util.List;

@Log4j
@Controller
@RequestMapping("/console")
public class ConsoleController {

    @Autowired
    UserService us;

    @Autowired
    RoleService rs;

    @Autowired
    PasswordEncoder encoder;

//    @Autowired
//    AuthorityService as;

    public static final String GET_USER = "/user/{id}";  //get
    public static final String ADD_USER = "/user/add";  //post
    public static final String UPDATE_USER = "/user/update";  //put
    public static final String DELETE_USER = "/user/delete/{id}";  //get
    public static final String GET_ALL_USERS = "/user/all";  //get

    public static final String GET_ROLE = "/role/{id}";  //get
    public static final String ADD_ROLE = "/role/add";  //post
    public static final String UPDATE_ROLE = "/role/update";  //put
    public static final String DELETE_ROLE = "/role/delete/{id}";  //get
    public static final String GET_ALL_ROLES = "/role/all";  //get

    public static final String SET_ROLE = "/authority/set";  //post
    public static final String UNSET_ROLE = "/authority/unset";  //post
    public static final String UNSET_ROL = "/authority/uuuunset";  //post



    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String consoleReturn(Model model){
        model.addAttribute("users", us.getAll());
        model.addAttribute("roles", rs.getAll());
//        model.addAttribute("authorities", as.getAllAuthority());
        return "console";
    }

    @RequestMapping(value = GET_USER, method = RequestMethod.GET)
    public User getUser(@PathVariable(value = "id", required = true) Long id){
        return us.getUser(id);
    }

    @RequestMapping(value = GET_ALL_USERS, method = RequestMethod.GET)
    public List<User> getAll(){
        return us.getAll();
    }

    @RequestMapping(value = ADD_USER, method = RequestMethod.POST)
    public void addUser(@RequestBody User user){
        us.addUser(user);
    }

    @RequestMapping(value = UPDATE_USER, method = RequestMethod.POST)
    public String updateUser(@RequestParam(value = "id", required = false, defaultValue = "-1") Long id,
                           @RequestParam(value = "name", required = false, defaultValue = "-1") String userName,
                           @RequestParam(value = "login", required = false, defaultValue = "-1") String login,
                           @RequestParam(value = "password", required = false, defaultValue = "-1") String password ){
        if (id==-1 || !us.userExists(id)){
            User user = new User(login, encoder.encode(password));
            us.addUser(user);
        } else {
            User user = us.getUser(id);
            user.setName(userName.equals("-1")?user.getName():userName);
            user.setLogin(login.equals("-1")?user.getLogin():login);
            user.setPassword(password.equals("-1")?user.getPassword():encoder.encode(password));
            us.updateUser(user);
        }
        return "redirect:/console/";
    }

    @RequestMapping(value = DELETE_USER, method = RequestMethod.GET)
    public String deleteUser(@PathVariable(value = "id", required = true) Long id){
        us.deleteUser(id);
        return "redirect:/console/";
    }

    @RequestMapping(value = GET_ROLE, method = RequestMethod.GET)
    public Role getRole(@PathVariable(value = "id", required = true) Long id){
        return rs.getRole(id);
    }

    @RequestMapping(value = GET_ALL_ROLES, method = RequestMethod.GET)
    public List<Role> getAllRoles(){
        return rs.getAll();
    }

    @RequestMapping(value = ADD_ROLE, method = RequestMethod.POST)
    public void addRole(@RequestBody Role role){
        rs.addRole(role);
    }

    @RequestMapping(value = UPDATE_ROLE, method = RequestMethod.POST)
    public String updateRole(@RequestParam(value = "id", required = false, defaultValue = "-1") Long id,
                           @RequestParam(value = "name", required = false, defaultValue = "-1") String roleName){
        if (id==-1 || !rs.roleExists(id)){
            Role role = new Role(roleName);
            rs.addRole(role);
        } else {
            Role role = rs.getRole(id);
            role.setRole(roleName.equals("-1")?role.getRole():roleName);
            rs.updateRole(role);
        }
        return "redirect:/console/";
    }

    @RequestMapping(value = DELETE_ROLE, method = RequestMethod.GET)
    public String deleteRole(@PathVariable(value = "id", required = true) Long id){
        rs.deleteRole(id);
        return "redirect:/console/";
    }

    @RequestMapping(value = SET_ROLE, method = RequestMethod.POST)
    public String set(@RequestParam(value = "user")String login, @RequestParam(value = "role")Long roleId ){

        User user = us.getUser(login);
        Role role = rs.getRole(roleId);
//        user.getRolesList().add(role);
        role.getUsers().add(user);
        us.updateUser(user);
        rs.updateRole(role);
        log.info("role " + role.getRole()+"was succesfully added to user "+login);
        return "redirect:/console/";
    }

    @RequestMapping(value = UNSET_ROLE, method = RequestMethod.POST)
    public String unSet(@RequestParam(value = "user")String login, @RequestParam(value = "role")Long roleId ){

        User user = us.getUser(login);
        Role role = rs.getRole(roleId);
//        user.getRolesList().remove(role);
        role.getUsers().remove(user);
        us.updateUser(user);
        rs.updateRole(role);
        log.info("role " + role.getRole()+"was succesfully deleted from user "+login);
        return "redirect:/console/";
    }
}
