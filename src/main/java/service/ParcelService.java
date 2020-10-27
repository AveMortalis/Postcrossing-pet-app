package service;


import dao.*;
import entity.AwaitList;
import entity.Parcel;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class ParcelService implements IParcelService {

    private IParcelDao parcelDao;

    private IWaiters awaitListDao;

    private IUserDao userDao;

    @Autowired
    public ParcelService(IParcelDao parcelDao, IWaiters awaitListDao,IUserDao userDao) {
        this.parcelDao = parcelDao;
        this.awaitListDao = awaitListDao;
        this.userDao=userDao;
    }

    @Transactional
    public List<Parcel> getAll(){
        return parcelDao.getAll();
    }

    @Transactional
    public void registerParcelByRegcode(String regcode, User recipient){

        Parcel parcel=parcelDao.getParcelByRecipientAndRegcode(recipient,regcode);
        if(parcel!=null) {
            parcel.setStatus("Received");
            awaitListDao.addUserToAwaitList(parcel.getMailer());
        }
    }

    @Transactional
    public Parcel addNewParcel(User mailer){
        AwaitList awaitList=awaitListDao.getFirstRecordFromAwaitList(mailer);
        User recipient;
        if (awaitList==null){
            recipient = userDao.getRandomUserButNotCurrent(mailer);
        }else {
            recipient= awaitList.getUser();
            awaitListDao.delete(awaitList);
        }
        LocalDate sendDate= LocalDate.now();

        Parcel parcel = new Parcel();
        parcel.setSendDate(Date.valueOf(sendDate));
        parcel.setStatus("Sended");
        parcel.setMailer(mailer);
        parcel.setRecipient(recipient);
        String mailerCountry = mailer.getAddress().getCountry().getCountryShortcut();
        String recipientCountry = recipient.getAddress().getCountry().getCountryShortcut();
        parcel.setRegistrationCode(generateRegcode(mailerCountry,recipientCountry));
        parcelDao.add(parcel);
        return parcel;
    }

    @Transactional
    public List<Parcel> getAllSendedByUser(User user){
        return parcelDao.getAllSendedUserParcels(user);
    }

    @Transactional
    public List<Parcel> getAllReceivedByUser(User user){
        return parcelDao.getAllReceivedParcelsByUser(user);
    }

    private String generateRegcode(String mailerCountry,String recipientCountry){
        String regcode = (mailerCountry+(int)(Math.random() * 1000000) + recipientCountry);
        return regcode;
    }

    @Transactional
    public List<Parcel> getLastParsels(final int COUNT_OF_PARCELS_TO_DISPLAY){
        List<Parcel> parcels=getAll();
        if(parcels.size()>COUNT_OF_PARCELS_TO_DISPLAY){
            parcels =parcels.subList(parcels.size()-COUNT_OF_PARCELS_TO_DISPLAY,parcels.size());}
        return parcels;
    }

    @Transactional
    public List<Parcel> getLastSendedUserParsels(final int COUNT_OF_PARCELS_TO_DISPLAY,User user){
        List<Parcel> parcels=getAllSendedByUser(user);
        if(parcels!=null) {
            if (parcels.size() > COUNT_OF_PARCELS_TO_DISPLAY) {
                parcels = parcels.subList(parcels.size() - COUNT_OF_PARCELS_TO_DISPLAY, parcels.size());
            }
        }
        return parcels;
    }

    @Transactional
    public int getCountOfParcels(){
       return parcelDao.getCountOfParcels();
    }

    @Transactional
    public int getCountOfReceivedParcels(){
        return parcelDao.getCountOfReceivedParcels();
    }



}
