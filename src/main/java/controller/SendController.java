package controller;

import dao.AwaitListDao;
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

@WebServlet(urlPatterns = {"/send"})
public class SendController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        User mailer = (User) req.getSession().getAttribute("user");

        UserDao userDao = new UserDao(session);

        AwaitListDao awaitListDao = new AwaitListDao(session);

        AwaitList awaitList = awaitListDao.getFirst(mailer);

        User recipient = new User();

        if (awaitList != null) {

            recipient = userDao.getUserById(awaitList.getUser_id());
            awaitListDao.delete(awaitList);
            req.setAttribute("recipient", recipient);
            Parcel parcel = new Parcel();
            parcel.setMailer(mailer);
            parcel.setRecipient(recipient);
            String regcode = (int) (Math.random() * 1000000) + mailer.getAddress().getCountryName();
            req.setAttribute("regcode", regcode);
            parcel.setRegistrationCode(regcode);
            parcel.setStatus("Sended");
            session.save(parcel);
            tx.commit();
            session.close();
            req.getRequestDispatcher("send.jsp").forward(req, resp);
        } else {
            String errorMessage="К сожалению в данный момент отсутсвуют люди для обмена";
            req.setAttribute("errorMessage",errorMessage);
            req.getRequestDispatcher("send.jsp").forward(req, resp);
            tx.rollback();
            session.close();
        }

    }
}
