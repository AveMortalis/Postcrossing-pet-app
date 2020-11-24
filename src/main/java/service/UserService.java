package service;

import dao.*;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {

    private IUserDao userDao;

    private IQueueingRecipientDao awaitListDao;

    private IParcelDao parcelDao;

    @Autowired
    public UserService(IUserDao userDao, IQueueingRecipientDao awaitListDao, IParcelDao parcelDao) {
        this.userDao = userDao;
        this.awaitListDao = awaitListDao;
        this.parcelDao=parcelDao;
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
        return userDao.getTotalCountOfUsers();
    }

    @Transactional
    public int getCountOfCountries(){
        return userDao.getTotalCountOfCountries();
    }

    public User getRandomUserButNotCurrent(User currentUser){
        return userDao.getRandomUserButNotCurrent(currentUser);
    }

    @Transactional
    public boolean isSendLimitReached(User user){

        return getCountOfParcelsAvailableToSendByUser(user)==0;

    }

    @Transactional
    public int getCountOfParcelsAvailableToSendByUser(User user){

        int countOfSendedParcelsHaveReceived = parcelDao.getAllSentUserParcelsThatHaveBeenReceived(user).size();

        int countOfTravelingParcels = parcelDao.getAllTravelingUserParcels(user).size();

        int sendLimit=5+countOfSendedParcelsHaveReceived/20;

        if (sendLimit>50){
            sendLimit=50;
        }
        int availableToSend=sendLimit-countOfTravelingParcels;

        if(availableToSend>0){
            return availableToSend;
        }else {
            return 0;
        }
    }

}
