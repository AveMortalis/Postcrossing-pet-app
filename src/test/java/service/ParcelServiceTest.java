package service;

import dao.IParcelDao;
import dao.IUserDao;
import dao.IQueueingRecipientDao;
import entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ParcelServiceTest {

    @Mock
    IParcelDao parcelDao;

    @Mock
    IQueueingRecipientDao awaitListDao;

    @Mock
    IUserDao userDao;

    @InjectMocks
    ParcelService parcelService;

    @Test
    void addNewParcelWhenAwaitListIsEmpty() {
        User currentUser = new User();
        currentUser.setName("Current");
        Address currentUserAdress = new Address();
        Country currentUserCountry=new Country();
        currentUserCountry.setCountryName("Belarus");
        currentUserCountry.setCountryShortcut("BY");
        currentUserAdress.setCountry(currentUserCountry);
        currentUser.setAddress(currentUserAdress);

        User random = new User();
        random.setName("Random");
        Address randomUserAdress = new Address();
        Country randomUserCountry=new Country();
        randomUserCountry.setCountryName("Belarus");
        randomUserCountry.setCountryShortcut("BY");
        randomUserAdress.setCountry(randomUserCountry);
        random.setAddress(randomUserAdress);

        Mockito.when(awaitListDao.getFirstRecipientFromFromQueueingRecipientsButNotCurrent(any())).thenReturn(null);
        Mockito.when(userDao.getRandomUserButNotCurrent(currentUser)).thenReturn(random);

        Parcel parcel=parcelService.addNewParcel(currentUser);

        Assertions.assertEquals("Random",parcel.getRecipient().getName());
    }

    @Test
    void addNewParcelWhenAwaitListIsntEmpty() {
        User currentUser = new User();
        currentUser.setName("Current");
        Address currentUserAdress = new Address();
        Country currentUserCountry=new Country();
        currentUserCountry.setCountryName("Belarus");
        currentUserCountry.setCountryShortcut("BY");
        currentUserAdress.setCountry(currentUserCountry);
        currentUser.setAddress(currentUserAdress);

        User userFromWaitList = new User();
        userFromWaitList.setName("UserFromAwaitList");
        Address userFromWaitListAdress = new Address();
        Country userFromWaitListCountry=new Country();
        userFromWaitListCountry.setCountryName("Belarus");
        userFromWaitListCountry.setCountryShortcut("BY");
        userFromWaitListAdress.setCountry(userFromWaitListCountry);
        userFromWaitList.setAddress(userFromWaitListAdress);

        QueueingRecipient queueingRecipient = new QueueingRecipient();
        queueingRecipient.setUser(userFromWaitList);

        Mockito.when(awaitListDao.getFirstRecipientFromFromQueueingRecipientsButNotCurrent(currentUser)).thenReturn(queueingRecipient);


        Parcel parcel=parcelService.addNewParcel(currentUser);

        Assertions.assertEquals("UserFromAwaitList",parcel.getRecipient().getName());
    }
}