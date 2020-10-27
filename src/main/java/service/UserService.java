package service;

import dao.*;
import entity.AwaitList;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {

    private IUserDao userDao;

    private IWaiters awaitListDao;

    private IParcelDao parcelDao;

    @Autowired
    public UserService(IUserDao userDao, IWaiters awaitListDao,IParcelDao parcelDao) {
        this.userDao = userDao;
        this.awaitListDao = awaitListDao;
        this.parcelDao=parcelDao;
    }

    @Transactional
    public User logIn(User userFromFrom){
        User user=userDao.login(userFromFrom.getLogin(),userFromFrom.getPassword());
        return user;
    }

    @Transactional
    public void updateUserDetails(User user){
        userDao.updateUser(user);
    }

    @Transactional
    public User getUserByLogin(String login){
        return userDao.getUserByLogin(login);
    }

    @Transactional
    public void saveUser(User user){
        userDao.addUser(user);
    }

    @Transactional
    public User getUserById(int id){
        return userDao.getUserById(id);
    }

    @Transactional
    public int getCountOfUsers(){
        return userDao.getCountOfUsers();
    }

    @Transactional
    public int getCountOfCountries(){
        return userDao.getCountOfCountries();
    }

    public User getRandomUserButNotCurrent(User currentUser){
        return userDao.getRandomUserButNotCurrent(currentUser);
    }

    @Transactional
    public boolean isSendLimitReached(User user){
        int countOfSendedParcelsHaveReceived = parcelDao.getAllSendedUserParcelsHaveReceived(user).size();
        int countOfTravelingParcels = parcelDao.getAllTravelingUserParcels(user).size();
        int sendLimit=5;
        if (countOfSendedParcelsHaveReceived<=5){
            return 5<=countOfTravelingParcels?true:false;
        }else if (countOfSendedParcelsHaveReceived<=15){
            return 6<=countOfTravelingParcels?true:false;
        }else if (countOfSendedParcelsHaveReceived<=25){
            return 7<=countOfTravelingParcels?true:false;
        }else if (countOfSendedParcelsHaveReceived<=35){
            return 8<=countOfTravelingParcels?true:false;
        }else if (countOfSendedParcelsHaveReceived<=45){
            return 9<=countOfTravelingParcels?true:false;
        }else if (countOfSendedParcelsHaveReceived<=55){
            return 10<=countOfTravelingParcels?true:false;
        }else if (countOfSendedParcelsHaveReceived<=85){
            return 11<=countOfTravelingParcels?true:false;
        }else if (countOfSendedParcelsHaveReceived<=110){
            return 12<=countOfTravelingParcels?true:false;
        }else if (countOfSendedParcelsHaveReceived<=150){
            return 14<=countOfTravelingParcels?true:false;
        }else if (countOfSendedParcelsHaveReceived>150){
            return ((int)(14+(countOfSendedParcelsHaveReceived-150)/50))<=countOfTravelingParcels?true:false;
        }
        return false;
    }

    @Transactional
    public int availableToSendByUser(User user){
        int countOfSendedParcelsHaveReceived = parcelDao.getAllSendedUserParcelsHaveReceived(user).size();
        int countOfTravelingParcels = parcelDao.getAllTravelingUserParcels(user).size();
        int sendLimit=5;
        if (countOfSendedParcelsHaveReceived<=5){
            return 5-countOfTravelingParcels;
        }else if (countOfSendedParcelsHaveReceived<=15){
            return 6-countOfTravelingParcels;
        }else if (countOfSendedParcelsHaveReceived<=25){
            return 7-countOfTravelingParcels;
        }else if (countOfSendedParcelsHaveReceived<=35){
            return 8-countOfTravelingParcels;
        }else if (countOfSendedParcelsHaveReceived<=45){
            return 9-countOfTravelingParcels;
        }else if (countOfSendedParcelsHaveReceived<=55){
            return 10-countOfTravelingParcels;
        }else if (countOfSendedParcelsHaveReceived<=85){
            return 11-countOfTravelingParcels;
        }else if (countOfSendedParcelsHaveReceived<=110){
            return 12-countOfTravelingParcels;
        }else if (countOfSendedParcelsHaveReceived<=150){
            return 14-countOfTravelingParcels;
        }else if (countOfSendedParcelsHaveReceived>150){
            return ((int)(14+(countOfSendedParcelsHaveReceived-150)/50))-countOfTravelingParcels;
        }
        return 0;
    }


    @Transactional
    public void searchForLostUserParcels(User user){
        userDao.searchingForLostUserParcels(user);
    }

}
