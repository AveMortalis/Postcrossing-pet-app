package controller;

import dao.UserDao;
import entity.Address;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import service.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( urlPatterns= {"/edit"})
public class EditUserController extends HttpServlet {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session= sessionFactory.openSession();
        String erorMessage = "";
        boolean correctUserData = true;
        String userName = req.getParameter("name");
        String userPassword = req.getParameter("password");
        String userEmail = req.getParameter("email");
        String userSurname=req.getParameter("surname");
        String userCity=req.getParameter("city");
        String userCountry=req.getParameter("country");
        String userAddress=req.getParameter("address");
        String userPostcode=req.getParameter("postcode");

        if (userName != "") {
            userName.trim();
        } else {
            correctUserData = false;
            erorMessage = erorMessage + "Заполните поле Name <br>";
        }

        if (userPassword != "") {
            userPassword.trim();
        } else {
            correctUserData = false;
            erorMessage = erorMessage + "Заполните поле Password <br>";
        }

        if (userEmail != "") {
            userEmail.trim();
        } else {
            correctUserData = false;
            erorMessage = erorMessage + "Заполните поле Email <br>";
        }

        if(correctUserData){
            Transaction tx=session.beginTransaction();
            UserDao userDao=new UserDao(session);
            User user=(User)req.getSession().getAttribute("user");
            user.setEmail(userEmail);
            user.setPassword(userPassword);
            user.setName(userName);
            user.setSurname(userSurname);
            Address address=new Address();
            address.setPostcode(Integer.parseInt(userPostcode));
            address.setCity(userCity);
            address.setCountryName(userCountry);
            address.setAddress(userAddress);
            user.setAddress(address);
            userDao.updateUser(user);
            tx.commit();
            resp.sendRedirect("index.jsp");
        }else{
            req.setAttribute("errorMessage",erorMessage);
            req.getRequestDispatcher("editUser.jsp").forward(req,resp);
        }
    }
}
