package controller;

import dao.AwaitListDao;
import dao.ParcelDao;
import dao.UserDao;
import entity.AwaitList;
import entity.Parcel;
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
import java.util.List;

@WebServlet( urlPatterns= {"/regParcel"})
public class RegisterParcelController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        User recipient = (User) req.getSession().getAttribute("user");
        UserDao userDao=new UserDao(session);
        ParcelDao parcelDao=new ParcelDao(session);
        AwaitListDao awaitListDao=new AwaitListDao(session);
        String regcode=req.getParameter("regcode");
        List<Parcel> parcelList=parcelDao.getParcelByRecipientAndRegcode(recipient,regcode);
        if(parcelList.isEmpty()==false){
            Parcel parcel=parcelList.get(0);
            parcel.setStatus("Recived");
            awaitListDao.add(parcel.getMailer());
            parcelDao.update(parcel);
            tx.commit();
            resp.sendRedirect("index.jsp");

        }else {
            String errorMessage="Вы ввели некорректный код, попробуйте снова";
            req.setAttribute("errorMessage",errorMessage);
            req.getRequestDispatcher("registerParcel.jsp").forward(req,resp);
        }
    }
}
