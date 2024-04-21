package service;

import dao.*;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {

    private IUserDao userDao;

    private IParcelDao parcelDao;

    @Autowired
    public UserService(IUserDao userDao, IParcelDao parcelDao) {
        this.userDao = userDao;
        this.parcelDao=parcelDao;
    }

    @Transactional
    public void updateUserDetails(User user){
        userDao.updateUser(user);
    }

    @Transactional(readOnly = true)
    public User getUserByLogin(String login){
        return userDao.getUserByLogin(login);
    }

    @Transactional
    public void saveUser(User user){
        userDao.addUser(user);
    }

    @Transactional(readOnly = true)
    public User getUserById(int id){
        return userDao.getUserById(id);
    }

    @Transactional(readOnly = true)
    public int getTotalCountOfUsers(){
        return userDao.getTotalCountOfUsers();
    }

    @Transactional(readOnly = true)
    public int getTotalCountOfCountries(){
        return userDao.getTotalCountOfCountries();
    }

    @Transactional(readOnly = true)
    public User getRandomUserButNotCurrent(User currentUser){
        return userDao.getRandomUserButNotCurrent(currentUser);
    }

    @Transactional
    public boolean isSendLimitReached(User user){ return getCountOfParcelsAvailableToSendByUser(user)==0;}

    @Transactional(readOnly = true)
    public int getCountOfParcelsAvailableToSendByUser(User user){

        int countOfSentParcelsThatHaveBeenReceived = parcelDao.getAllSentUserParcelsThatHaveBeenReceived(user).size();
        int countOfTravelingParcels = parcelDao.getAllTravelingUserParcels(user).size();
        int sendLimit=5+countOfSentParcelsThatHaveBeenReceived/20;

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
