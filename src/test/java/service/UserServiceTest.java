package service;

import dao.IParcelDao;
import dao.IUserDao;
import dao.IQueueingRecipientDao;
import entity.Parcel;
import entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private IUserDao userDao;

    @Mock private IQueueingRecipientDao awaitListDao;

    @Mock private IParcelDao parcelDao;

    @Test
    void isSendLimitReachedReturnsFalseWhenSendLimitIsReached(){
        ArrayList<Parcel> allRecieved=new ArrayList<>(4);
        allRecieved.add(new Parcel());
        allRecieved.add(new Parcel());
        allRecieved.add(new Parcel());
        allRecieved.add(new Parcel());

        ArrayList<Parcel> allTraveling=new ArrayList<>(4);
        allTraveling.add(new Parcel());
        allTraveling.add(new Parcel());
        allTraveling.add(new Parcel());
        allTraveling.add(new Parcel());
        allTraveling.add(new Parcel());
        allTraveling.add(new Parcel());
        allTraveling.add(new Parcel());


        User user=new User();
        Mockito.doReturn(allRecieved).when(parcelDao).getAllSentUserParcelsThatHaveBeenReceived(any(User.class));
        Mockito.doReturn(allTraveling).when(parcelDao).getAllTravelingUserParcels(any(User.class));
        IUserService userService=new UserService(userDao,awaitListDao,parcelDao);
        Assertions.assertTrue(userService.isSendLimitReached(user));
    }

    @Test
    void isSendLimitReachedReturnsTrueWhenSendLimitIsntReached(){
        ArrayList<Parcel> allRecieved=new ArrayList<>(4);
        allRecieved.add(new Parcel());
        allRecieved.add(new Parcel());
        allRecieved.add(new Parcel());
        allRecieved.add(new Parcel());
        allRecieved.add(new Parcel());
        allRecieved.add(new Parcel());


        ArrayList<Parcel> allTraveling=new ArrayList<>(4);
        allTraveling.add(new Parcel());
        allTraveling.add(new Parcel());
        allTraveling.add(new Parcel());


        User user=new User();
        Mockito.doReturn(allRecieved).when(parcelDao).getAllSentUserParcelsThatHaveBeenReceived(any(User.class));
        Mockito.doReturn(allTraveling).when(parcelDao).getAllTravelingUserParcels(any(User.class));
        IUserService userService=new UserService(userDao,awaitListDao,parcelDao);
        Assertions.assertFalse(userService.isSendLimitReached(user));
    }

    @Test
    void availableToSendByUser() {
    }
}