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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    @RequestMapping(value = "/recipientDetails",method = RequestMethod.GET)
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
    public String registerParcel(@AuthenticationPrincipal SecurityUser securityUser, HttpServletRequest request, RedirectAttributes redirectAttributes){


        User recipient = userService.getUserByLogin(securityUser.getUsername());
        boolean isRegistred=parcelService.registerParcelByRegcode(request.getParameter("regcode").trim(),recipient);
        if(!isRegistred){
            redirectAttributes.addAttribute("error","Invalid regcode");
            return "redirect:/parcelRegistration";
        }
        return "redirect:/userDetails";

    }

    @RequestMapping(value = "/parcelRegistration",method = RequestMethod.GET)
    public String getParcelRegistrationPage(Model model){

        String regcode="";
        model.addAttribute("regcode",regcode);
        return "parcelRegistration";

    }

    @RequestMapping(value = "/parcelStats", method = RequestMethod.GET)
    public String getOverallParcelStats(Model model,@AuthenticationPrincipal SecurityUser securityUser){

        model.addAttribute("parcels",parcelService.getAll());
        return "parcelStats";
    }

    @RequestMapping(value = "/myParcelStats", method = RequestMethod.GET)
    public String getUserParcelStats(Model model,@AuthenticationPrincipal SecurityUser securityUser){

        if(securityUser!=null){
            User user = userService.getUserByLogin(securityUser.getUsername());
            model.addAttribute("userSendedParcels",parcelService.getAllSentByUser(user));
            model.addAttribute("userRecivedParcels",parcelService.getAllReceivedByUser(user));
        }
        return "myParcelStats";
    }

}

