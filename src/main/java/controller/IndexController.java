package controller;

import entity.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.IParcelService;
import service.IUserService;
import service.ParcelService;
import service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    private IParcelService parcelService;
    private IUserService userService;

    @Autowired
    public IndexController(IParcelService parcelService, IUserService userService) {
        this.parcelService = parcelService;
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaulttt(Model model, HttpSession session) {
        model.addAttribute("parcels", parcelService.getLastParsels(12));
        model.addAttribute("countOfParcels", parcelService.getCountOfParcels());
        model.addAttribute("countOfReceivedParcels", parcelService.getCountOfReceivedParcels());
        model.addAttribute("countOfCountries", userService.getCountOfCountries());
        model.addAttribute("countOfUsers", userService.getCountOfUsers());
        return "index";
    }

    @RequestMapping(value = "faq", method = RequestMethod.GET)
    public String faq(Model model){

        System.out.println( SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return "faq";
    }
}
