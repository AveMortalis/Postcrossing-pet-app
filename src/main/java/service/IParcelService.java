package service;

import entity.Parcel;
import entity.User;

import java.util.List;

public interface IParcelService {

    public List<Parcel> getAll();

    public boolean registerParcelByRegcode(String regcode, User recipient);

    public Parcel addNewParcel(User mailer);

    public List<Parcel> getAllSentByUser(User user);

    public List<Parcel> getAllReceivedByUser(User user);

    public List<Parcel> getLastParcels(final int countOfParcelsToDisplay);

    public List<Parcel> getLastSentUserParcels(final int countOfParcelsToDisplay, User user);

    public int getTotalCountOfParcels();

    public int getTotalCountOfReceivedParcels();

    public void searchForLostParcelsAndMarkThemAsLost();
}
