package controller;

import dao.AwaitListDao;
import dao.ParcelDao;
import dao.UserDao;
import entity.AwaitList;
import entity.Parcel;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import service.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet( urlPatterns= {"/regParcel"})
public class RegisterParcelController extends HttpServlet {

//    private UserDao userDao;
//    private ParcelDao parcelDao;
//    private AwaitListDao awaitListDao;
//
//    @Autowired
//    public RegisterParcelController(UserDao userDao, ParcelDao parcelDao, AwaitListDao awaitListDao) {
//        this.userDao = userDao;
//        this.parcelDao = parcelDao;
//        this.awaitListDao = awaitListDao;
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Session session= HibernateUtil.getSessionFactory().openSession();
//        Transaction tx=session.beginTransaction();
//        User recipient = (User) req.getSession().getAttribute("user");
//        String regcode=req.getParameter("regcode");
//        List<Parcel> parcelList=parcelDao.getParcelByRecipientAndRegcode(recipient,regcode);
//        if(parcelList.isEmpty()==false){
//            Parcel parcel=parcelList.get(0);
//            parcel.setStatus("Recived");
//            awaitListDao.add(parcel.getMailer());
//            parcelDao.update(parcel);
//            tx.commit();
//            resp.sendRedirect("index.jsp");
//
//        }else {
//            String errorMessage="Вы ввели некорректный код, попробуйте снова";
//            req.setAttribute("errorMessage",errorMessage);
//            req.getRequestDispatcher("registerParcel.jsp").forward(req,resp);
//        }
//    }
}
