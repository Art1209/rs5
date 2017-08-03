package rs5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs5.persistence.entity.Role;
import rs5.persistence.entity.User;
import rs5.persistence.service.RoleService;
import rs5.persistence.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/rest")
public class RestController {

    @Autowired
    UserService us;

    @Autowired
    RoleService rs;

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



    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String consoleReturn(){
        return "console";
    }

    @ResponseBody
    @RequestMapping(value = GET_USER, method = RequestMethod.GET)
    public User getUser(@PathVariable(value = "id", required = true) Long id){
        return us.getUser(id);
    }

    @ResponseBody
    @RequestMapping(value = GET_ALL_USERS, method = RequestMethod.GET)
    public List<User> getAll(){
        return us.getAll();
    }

    @RequestMapping(value = ADD_USER, method = RequestMethod.POST)
    public void addUser(@RequestBody User user){
        us.addUser(user);
    }

    @RequestMapping(value = UPDATE_USER, method = RequestMethod.POST)
    public void updateUser(@RequestBody User user){
        us.updateUser(user);
    }

    @RequestMapping(value = DELETE_USER, method = RequestMethod.GET)
    public void deleteUser(@PathVariable(value = "id", required = true) Long id){
        us.deleteUser(id);
    }

    @ResponseBody
    @RequestMapping(value = GET_ROLE, method = RequestMethod.GET)
    public Role getRole(@PathVariable(value = "id", required = true) Long id){
        return rs.getRole(id);
    }

    @ResponseBody
    @RequestMapping(value = GET_ALL_ROLES, method = RequestMethod.GET)
    public List<Role> getAllRoles(){
        return rs.getAll();
    }

    @RequestMapping(value = ADD_ROLE, method = RequestMethod.POST)
    public void addRole(@RequestBody Role role){
        rs.addRole(role);
    }

    @RequestMapping(value = UPDATE_ROLE, method = RequestMethod.POST)
    public void updateRole(@RequestBody Role role){
        rs.updateRole(role);
    }

    @RequestMapping(value = DELETE_ROLE, method = RequestMethod.GET)
    public void deleteRole(@PathVariable(value = "id", required = true) Long id){
        rs.deleteRole(id);
    }

}
