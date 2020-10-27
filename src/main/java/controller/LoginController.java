package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.IUserService;
import service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController  {

    private IUserService userService;

    @Autowired
    public LoginController(IUserService userService) {
        this.userService=userService;
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String initLoginForm(Model model){
        return "login";
    }

//    @RequestMapping(value = "login",method = RequestMethod.POST)
//    public String checkLogin(@ModelAttribute User userFromForm, Model model, SecurityContextHolder contextHolder){
//
//        User user = userService.logIn(userFromForm);
//        contextHolder
//
////        if(user!=null){
////            userService.searchForLostUserParcels(user);
////            session.setAttribute("auth",true);
////            session.setAttribute("user",user);
////            session.setAttribute("mess","Вы успешно залогинились");
////        }else {
////            session.setAttribute("mess","Вы ввели неверные данные");
////        }
//        return "redirect:/userDetails";
//
//    }
}
