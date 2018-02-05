package controller;

import dao.UserDao;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
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
import java.util.Map;

@Controller
public class LoginController  {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService=userService;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String defaulttt(){
        return "index";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String initLoginForm(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String checkLogin(@ModelAttribute User userFromForm, Model model, HttpSession session){

        User user = userService.logIn(userFromForm);
        if(user!=null){
            session.setAttribute("auth",true);
            session.setAttribute("user",user);
            session.setAttribute("mess","Вы успешно залогинились");
        }else {
            session.setAttribute("mess","Вы ввели неверные данные");
        }
        return "userDetails";
    }
}
