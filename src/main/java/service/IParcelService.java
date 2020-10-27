package service;

import entity.AwaitList;
import entity.Parcel;
import entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface IParcelService {

    public List<Parcel> getAll();

    public void registerParcelByRegcode(String regcode, User recipient);

    public Parcel addNewParcel(User mailer);

    public List<Parcel> getAllSendedByUser(User user);

    public List<Parcel> getAllReceivedByUser(User user);

    public List<Parcel> getLastParsels(final int countOfParcelsToDisplay);

    public List<Parcel> getLastSendedUserParsels(final int countOfParcelsToDisplay,User user);

    public int getCountOfParcels();

    public int getCountOfReceivedParcels();
}
