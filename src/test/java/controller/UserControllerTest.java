package controller;

import entity.Address;
import entity.Country;
import entity.Parcel;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import service.IParcelService;
import service.IUserService;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
class UserControllerTest {


    MockMvc mockMvc;

    @Mock
    private IUserService userService;

    @Mock
    private IParcelService parcelService;

    @Mock
    private PasswordEncoder passwordEncoder;




    @BeforeEach
    void setup() {
        ThymeleafViewResolver resolver= new ThymeleafViewResolver();
        ISpringTemplateEngine tmpl= new SpringTemplateEngine();
        resolver.setTemplateEngine(tmpl);

        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService,parcelService,passwordEncoder)).setViewResolvers(resolver).build();
    }

    @Test
    void initRegistrationPage() throws Exception {
        this.mockMvc.perform(get("/registration")).andExpect(status().isOk());
    }

    @Test
    void registration() {
    }

    @Test
    void initUpdateUserDetailsPage() {
    }

    @Test
    void updateUserDetails() {
    }

    @Test
    void showUserDetails() throws Exception {

        User user = new User();
        user.setName("Current");
        Address currentUserAdress = new Address();
        Country currentUserCountry=new Country();
        currentUserCountry.setCountryName("Belarus");
        currentUserCountry.setCountryShortcut("BY");
        currentUserAdress.setCountry(currentUserCountry);
        user.setAddress(currentUserAdress);

        ArrayList<Parcel> lastSent=new ArrayList<>(4);
        lastSent.add(new Parcel());
        lastSent.add(new Parcel());
        lastSent.add(new Parcel());

        when(userService.getUserByLogin(any())).thenReturn(user);
        when(userService.availableToSendByUser(user)).thenReturn(1);
        when(parcelService.getLastSentUserParcels(5,user)).thenReturn(lastSent);
        this.mockMvc.perform(get("/userDetails"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("last5sent"))
                .andExpect(model().attribute("last5sent", hasItems (lastSent.toArray())))
                .andExpect(model().attributeExists("availableToSend"))
                .andExpect(model().attribute("availableToSend", equalTo(1)))
                .andExpect(view().name("userDetails"));
    }
}