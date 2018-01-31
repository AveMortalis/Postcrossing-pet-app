package controller;

import dao.ParcelDao;
import entity.Parcel;
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

@WebServlet( urlPatterns= {"/parcelStats"})
public class ParcelStatsController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        ParcelDao parcelDao=new ParcelDao(session);
        List<Parcel> parcels=parcelDao.getAll();
        req.setAttribute("parcels",parcels);
        tx.commit();
        session.close();
        req.getRequestDispatcher("parcelStats.jsp").forward(req,resp);
    }
}
