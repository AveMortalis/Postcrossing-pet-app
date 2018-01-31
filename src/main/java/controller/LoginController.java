package controller;

import dao.UserDao;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import service.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session=HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        UserDao userDao=new UserDao(session);

        String login=req.getParameter("login");
        String pass=req.getParameter("password");

        User user=userDao.login(login,pass);
        if(user!=null){
            req.getSession().setAttribute("auth",true);
            req.getSession().setAttribute("user",user);
            req.setAttribute("mess","Вы успешно залогинились");
        }else {
            req.setAttribute("mess","Вы ввели неверные данные");
        }

        tx.commit();
        session.close();
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
}
