package controller;

import dao.UserDao;
import entity.Address;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.HibernateUtil;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String InitRegistrationPage(Model model){
        User usertoFill=new User();
        model.addAttribute("user",usertoFill);
        model.addAttribute("actionType","registration");
        return "registrationOrUpdate";
    }

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public String registration(@ModelAttribute User user){
        userService.saveUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String InitUpdateUserDetailsPage(Model model,HttpSession session)
    {
        User userToUpdate=(User)session.getAttribute("user");
        model.addAttribute("user",userToUpdate);
        model.addAttribute("actionType","update");
        return "registrationOrUpdate";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String UpdateUserDetails(@ModelAttribute User user,HttpSession session) {
        System.out.println(user);
        userService.updateUserDetails(user);
        session.setAttribute("user",user);
        return "userDetails";
    }


}
