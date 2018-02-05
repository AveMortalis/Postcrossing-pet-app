package controller;

import dao.ParcelDao;
import entity.Parcel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.HibernateUtil;
import service.ParcelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class ParcelStatsController {

    private ParcelService parcelService;

    @Autowired
    public ParcelStatsController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @RequestMapping(value = "parcelStats", method = RequestMethod.GET)
    public String getParcelStats(Model model){
        model.addAttribute("parcels",parcelService.getAll());
        return "parcelStats";
    }
}
