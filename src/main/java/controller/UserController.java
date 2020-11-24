package controller;

import entity.Parcel;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import security.SecurityUser;
import service.IParcelService;
import service.IUserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private IUserService userService;

    private IParcelService parcelService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(IUserService userService, IParcelService parcelService, PasswordEncoder passwordEncoder){
        this.userService=userService;
        this.parcelService=parcelService;
        this.passwordEncoder=passwordEncoder;
    }

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String InitRegistrationPage(Model model){

        User usertoFill=new User();
        model.addAttribute("user",usertoFill);
        model.addAttribute("stitle","Регистрация");
        model.addAttribute("actionType","registration");
        model.addAttribute("buttonTitle","Зарегистрироваться");
        return "regOrUpdate";
    }

    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public String registration(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("stitle","Регистрация");
            model.addAttribute("actionType","registration");
            model.addAttribute("buttonTitle","Зарегистрироваться");
            return "regOrUpdate";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String InitUpdateUserDetailsPage(Model model, @AuthenticationPrincipal SecurityUser securityUser)
    {


        User userToUpdate=userService.getUserByLogin(securityUser.getUsername());
        userToUpdate.setPassword("");
        model.addAttribute("user",userToUpdate);
        model.addAttribute("actionType","update");
        model.addAttribute("stitle","Изменение личных данных");
        model.addAttribute("buttonTitle","Сохранить");
        return "regOrUpdate";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String UpdateUserDetails(@Valid User user, BindingResult bindingResult,Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("actionType","update");
            model.addAttribute("stitle","Изменение личных данных");
            model.addAttribute("buttonTitle","Сохранить");
            return "regOrUpdate";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateUserDetails(user);
        return "redirect:/userDetails";
    }

    @RequestMapping(value = "/userDetails",method = RequestMethod.GET)
    public String ShowUserDetails(Model model,@AuthenticationPrincipal SecurityUser securityUser) {
        User user=userService.getUserByLogin(securityUser.getUsername());
        List<Parcel> last5=parcelService.getLastSentUserParcels(5,user);
        model.addAttribute("last5sent",last5);
        model.addAttribute("availableToSend",userService.getCountOfParcelsAvailableToSendByUser(user));
        return "userDetails";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String initLoginForm(Model model){
        return "login";
    }

    @RequestMapping(value = "logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }


}
