package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import security.SecurityUser;
import service.IParcelService;
import service.IUserService;


@Controller
public class ParcelStatsController {

    private IParcelService parcelService;
    private IUserService userService;

    @Autowired
    public ParcelStatsController(IParcelService parcelService, IUserService userService) {
        this.parcelService = parcelService;
        this.userService=userService;
    }

    @RequestMapping(value = "/parcelStats", method = RequestMethod.GET)
    public String getParcelStats(Model model,@AuthenticationPrincipal SecurityUser securityUser){

        model.addAttribute("parcels",parcelService.getAll());

        if(securityUser!=null){
            User user = userService.getUserByLogin(securityUser.getUsername());
            model.addAttribute("userSendedParcels",parcelService.getAllSentByUser(user));
            model.addAttribute("userRecivedParcels",parcelService.getAllReceivedByUser(user));
        }
        return "parcelStats";
    }
}
