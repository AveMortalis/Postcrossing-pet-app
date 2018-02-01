package controller;

import dao.UserDao;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
public class LoginController  {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        return "index";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String initLoginForm(Map<String,Object> model){
        User user=new User();
        model.put("user",user);
//        Session session=HibernateUtil.getSessionFactory().openSession();
//        Transaction tx=session.beginTransaction();
//        UserDao userDao=new UserDao(session);
//
//
//        String login=model.getParameter("login");
//        String pass=model.getParameter("password");
//        User user=userDao.login(login,pass);
//        if(user!=null){
//            model.getSession().setAttribute("auth",true);
//            model.getSession().setAttribute("user",user);
//            model.setAttribute("mess","Вы успешно залогинились");
//        }else {
//            req.setAttribute("mess","Вы ввели неверные данные");
//        }
//
//        tx.commit();
//        session.close();
//        req.getRequestDispatcher("/index.jsp").forward(req,resp);
        return "login";
    }

//    @RequestMapping(value = "login",method = RequestMethod.POST)
//    public String checkLogin(){
//
//    }
}
