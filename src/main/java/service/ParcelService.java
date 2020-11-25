package service;


import dao.*;
import entity.QueueingRecipient;
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

    private IQueueingRecipientDao queueingRecipientDao;

    private IUserDao userDao;

    @Autowired
    public ParcelService(IParcelDao parcelDao, IQueueingRecipientDao queueingRecipientDao,IUserDao userDao) {
        this.parcelDao = parcelDao;
        this.queueingRecipientDao = queueingRecipientDao;
        this.userDao=userDao;
    }

    @Transactional(readOnly = true)
    public List<Parcel> getAll(){
        return parcelDao.getAll();
    }

    @Transactional
    public boolean registerParcelByRegcode(String regcode, User recipient){

        Parcel parcel=parcelDao.getParcelByRecipientAndRegcode(recipient,regcode);
        if(parcel!=null) {
            parcel.setStatus("Received");
            LocalDate receiveDate= LocalDate.now();
            parcel.setReceiveDate(Date.valueOf(receiveDate));
            queueingRecipientDao.addUserToQueueingRecipients(parcel.getMailer());
            return true;
        }
        return false;
    }

    @Transactional
    public Parcel addNewParcel(User mailer){

        QueueingRecipient queueingRecipient =queueingRecipientDao.getFirstRecipientFromFromQueueingRecipientsButNotCurrent(mailer);
        User recipient;
        if (queueingRecipient ==null){
            recipient = userDao.getRandomUserButNotCurrent(mailer);
        }else {
            recipient= queueingRecipient.getUser();
            queueingRecipientDao.delete(queueingRecipient);
        }
        LocalDate sendDate= LocalDate.now();
        Parcel parcel = new Parcel();
        parcel.setSendDate(Date.valueOf(sendDate));
        parcel.setStatus("Sent");
        parcel.setMailer(mailer);
        parcel.setRecipient(recipient);
        String mailerCountry = mailer.getAddress().getCountry().getCountryShortcut();
        String recipientCountry = recipient.getAddress().getCountry().getCountryShortcut();
        parcel.setRegistrationCode(generateRegcode(mailerCountry,recipientCountry));
        parcelDao.add(parcel);
        return parcel;
    }

    @Transactional(readOnly = true)
    public List<Parcel> getAllParcelsSentByUser(User user){
        return parcelDao.getAllParcelsSentByUser(user);
    }

    @Transactional(readOnly = true)
    public List<Parcel> getAllParcelsReceivedByUser(User user){
        return parcelDao.getAllParcelsReceivedByUser(user);
    }

    private String generateRegcode(String mailerCountry,String recipientCountry){

        String regcode = (mailerCountry+(int)(Math.random() * 1000000) + recipientCountry);
        return regcode;
    }

    @Transactional(readOnly = true)
    public List<Parcel> getLastParcels(final int COUNT_OF_PARCELS_TO_DISPLAY){

        List<Parcel> parcels=getAll();
        if(parcels.size()>COUNT_OF_PARCELS_TO_DISPLAY){
            parcels =parcels.subList(parcels.size()-COUNT_OF_PARCELS_TO_DISPLAY,parcels.size());}
        return parcels;
    }

    @Transactional(readOnly = true)
    public List<Parcel> getLastParcelsSentByUser(final int COUNT_OF_PARCELS_TO_DISPLAY, User user){

        List<Parcel> parcels= getAllParcelsSentByUser(user);
        if(parcels!=null) {
            if (parcels.size() > COUNT_OF_PARCELS_TO_DISPLAY) {
                parcels = parcels.subList(parcels.size() - COUNT_OF_PARCELS_TO_DISPLAY, parcels.size());
            }
        }
        return parcels;
    }

    @Transactional(readOnly = true)
    public int getTotalCountOfParcels(){
       return parcelDao.getTotalCountOfParcels();
    }

    @Transactional(readOnly = true)
    public int getTotalCountOfReceivedParcels(){
        return parcelDao.getTotalCountOfReceivedParcels();
    }

    @Transactional
    public void searchForLostParcelsAndMarkThemAsLost(){
        parcelDao.searchForLostParcelsAndMarkThemAsLost();
    }


}
