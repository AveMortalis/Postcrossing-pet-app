package controller;

import dao.UserDao;
import entity.Address;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(urlPatterns = "/registration")
public class RegistrationController extends HttpServlet {

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        Session session= HibernateUtil.getSessionFactory().openSession();
//
//        String erorMessage = "";
//        boolean correctUserData = true;
//        String userName = req.getParameter("name");
//        String userLogin = req.getParameter("login");
//        String userPassword = req.getParameter("password");
//        String userEmail = req.getParameter("email");
//        String userSurname=req.getParameter("surname");
//        String userCity=req.getParameter("city");
//        String userCountry=req.getParameter("country");
//        String userAddress=req.getParameter("address");
//        String userPostcode=req.getParameter("postcode");
//        System.out.println(userName + " " + userLogin + " " + userPassword + " " + userEmail);
//        if (userName != "") {
//            userName.trim();
//        } else {
//            correctUserData = false;
//            erorMessage = erorMessage + "Заполните поле Name <br>";
//        }
//
//        if (userLogin != "") {
//            userLogin.trim();
//        } else {
//            correctUserData = false;
//            erorMessage = erorMessage + "Заполните поле Login <br> ";
//        }
//
//
//        if (userPassword != "") {
//            userPassword.trim();
//        } else {
//            correctUserData = false;
//            erorMessage = erorMessage + "Заполните поле Password <br>";
//        }
//
//
//        if (userEmail != "") {
//            userEmail.trim();
//        } else {
//            correctUserData = false;
//            erorMessage = erorMessage + "Заполните поле Email <br>";
//        }
//        if(correctUserData){
//            Transaction tx=session.beginTransaction();
//            UserDao userDao=new UserDao(session);
//            User user=new User();
//            user.setEmail(userEmail);
//            user.setLogin(userLogin);
//            user.setPassword(userPassword);
//            user.setName(userName);
//            user.setSurname(userSurname);
//            Address address=new Address();
//            address.setPostcode(Integer.parseInt(userPostcode));
//            address.setCity(userCity);
//            address.setCountryName(userCountry);
//            address.setAddress(userAddress);
//            user.setAddress(address);
//            userDao.addUser(user);
//            tx.commit();
//            resp.sendRedirect("login.jsp");
//        }else{
//            req.setAttribute("errorMessage",erorMessage);
//            req.getRequestDispatcher("reg.jsp").forward(req,resp);
//        }
//
//    }
}
