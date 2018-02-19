package service;

import dao.AwaitListDao;
import dao.ParcelDao;
import entity.AwaitList;
import entity.Parcel;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParcelService {

    private ParcelDao parcelDao;

    private AwaitListDao awaitListDao;

    @Autowired
    public ParcelService(ParcelDao parcelDao, AwaitListDao awaitListDao) {
        this.parcelDao = parcelDao;
        this.awaitListDao = awaitListDao;
    }

    @Transactional
    public List<Parcel> getAll(){
        return parcelDao.getAll();
    }

    @Transactional
    public void registerParcelByRegcode(String regcode, User recipient){

        Parcel parcel=parcelDao.getParcelByRecipientAndRegcode(recipient,regcode);
        parcel.setStatus("Recived");
        awaitListDao.addUserToAwaitList(parcel.getMailer());
    }

    @Transactional
    public Parcel addNewParcel(User mailer){
        AwaitList awaitList=awaitListDao.getFirstRecordFromAwaitList(mailer);
        User recipient = awaitList.getUser();
        awaitListDao.delete(awaitList);
        Parcel parcel = new Parcel();
        parcel.setStatus("Sended");
        parcel.setMailer(mailer);
        parcel.setRecipient(recipient);
        String mailerCountry = mailer.getAddress().getCountryName();
        String recipientCountry = recipient.getAddress().getCountryName();
        parcel.setRegistrationCode(generateRegcode(mailerCountry,recipientCountry));
        parcelDao.add(parcel);
        return parcel;
    }

    @Transactional
    public List<Parcel> getAllSendedByUser(User user){
        return parcelDao.getAllSendedUserParcels(user);
    }

    @Transactional
    public List<Parcel> getAllRecivedByUser(User user){
        return parcelDao.getAllRecivedUserParcels(user);
    }

    private String generateRegcode(String mailerCountry,String recipientCountry){
        String regcode = (mailerCountry+(int)(Math.random() * 1000000) + recipientCountry);
        return regcode;
    }



}
