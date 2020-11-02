package controller;

import entity.Parcel;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import security.SecurityUser;
import service.IParcelService;
import service.IUserService;
import service.ParcelService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ParcelController {

    private IParcelService parcelService;

    private IUserService userService;

    @Autowired
    public ParcelController(IParcelService parcelService, IUserService userService) {
        this.parcelService = parcelService;
        this.userService = userService;
    }

    @RequestMapping(value = "/getRecipentInfo",method = RequestMethod.GET)
    public String getParcelRecipient(@AuthenticationPrincipal SecurityUser securityUser, Model model){

        User mailer = userService.getUserByLogin(securityUser.getUsername());
        if(userService.isSendLimitReached(mailer)){
            return "faq";
        }else {
            Parcel parcel = parcelService.addNewParcel(mailer);
            model.addAttribute("regcode", parcel.getRegistrationCode());
            model.addAttribute("recipient", parcel.getRecipient());
            model.addAttribute("sendDate",parcel.getSendDate().toString());
            return "recipientInfo";
        }

    }

    @RequestMapping(value = "/parcelRegistration",method = RequestMethod.POST)
    public String registerParcel(@AuthenticationPrincipal SecurityUser securityUser, HttpServletRequest request){

        User recipient = userService.getUserByLogin(securityUser.getUsername());
        parcelService.registerParcelByRegcode(request.getParameter("regcode").trim(),recipient);
        return "redirect:/userDetails";

    }

    @RequestMapping(value = "/parcelRegistration",method = RequestMethod.GET)
    public String getRecipient(Model model){

        String regcode="";
        model.addAttribute("regcode",regcode);
        return "parcelRegistration";

    }

}

