package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.IParcelService;
import service.IUserService;

import javax.servlet.http.HttpSession;

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
    public String getMainPage(Model model) {

        model.addAttribute("parcels", parcelService.getLastParcels(12));
        model.addAttribute("countOfParcels", parcelService.getTotalCountOfParcels());
        model.addAttribute("countOfReceivedParcels", parcelService.getTotalCountOfReceivedParcels());
        model.addAttribute("countOfCountries", userService.getCountOfCountries());
        model.addAttribute("countOfUsers", userService.getCountOfUsers());
        return "index";
    }

    @RequestMapping(value = "faq", method = RequestMethod.GET)
    public String getFaqPage(){

        return "faq";

    }
}
