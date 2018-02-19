package controller;

import entity.Parcel;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.ParcelService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ParcelController {

    private ParcelService parcelService;

    private UserService userService;

    @Autowired
    public ParcelController(ParcelService parcelService, UserService userService) {
        this.parcelService = parcelService;
        this.userService = userService;
    }

    @RequestMapping(value = "getRecipentInfo",method = RequestMethod.GET)
    public String getParcelRecipient(HttpSession session,Model model){
        User mailer = (User) session.getAttribute("user");
        Parcel parcel = parcelService.addNewParcel(mailer);
        model.addAttribute("regcode",parcel.getRegistrationCode());
        model.addAttribute("recipient",parcel.getRecipient());
        return "recipientInfo";
    }

    @RequestMapping(value = "parcelRegistration",method = RequestMethod.POST)
    public String getRecipient(HttpSession session, HttpServletRequest request){

        User mailer = (User) session.getAttribute("user");
        parcelService.registerParcelByRegcode(request.getParameter("regcode").trim(),mailer);
        return "userDetails";
    }

    @RequestMapping(value = "parcelRegistration",method = RequestMethod.GET)
    public String getRecipient(Model model){
        String regcode="";
        model.addAttribute("regcode",regcode);
        return "parcelRegistration";
    }

}

