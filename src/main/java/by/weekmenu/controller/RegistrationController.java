package by.weekmenu.controller;

import by.weekmenu.DTO.AccountDTO;
import by.weekmenu.repos.AccountRepo;
import by.weekmenu.repos.RoleRepo;
import by.weekmenu.domain.User;
import by.weekmenu.repos.UserRepo;
import by.weekmenu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@RestController
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private UserService userSevice;

   /* @Bean
    public Principal principal(UserSevice userSevice){
        return userSevice.loadUserByUsername("");
    }*/



    @GetMapping("/registration")
    public ResponseEntity<Object> registration(HttpHeaders httpHeaders) throws IOException {
       httpHeaders.setLocation(URI.create("registration"));

        return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model, @RequestParam("family") String family) {
        User userFromDb = userRepo.findByUsername(user.getUsername());



        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

         User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPassword(user.getPassword());
        user1.setAccount(accountRepo.findByName(family));
        user1.setMobilephone(user.getMobilephone());
        user1.setRole(roleRepo.findByName("USER"));

        userRepo.save(user1);


        return "Hello";
    }

    @GetMapping("/")

    public String greeting(Map<String, Object> model) {



        return "reg";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {


        return "main";
    }

    @PostMapping("/account")
    public String addFamily(AccountDTO accountDTO) {


        System.out.println(accountDTO.getFamilyName());


        return "redirect:/";
    }


    @GetMapping("/account")
    public String add(){
        return "greeting";
    }


}
