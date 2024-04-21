package dao;

import entity.Parcel;
import entity.User;
import java.util.List;

public interface IParcelDao {

    public List<Parcel> getAll();

    public Parcel getParcelById(int id);

    public void delete(Parcel parcel);

    public void add(Parcel parcel);

    public void update(Parcel parcel);

    public void updateParcelStatus(Parcel parcel);

    public Parcel getParcelByRecipientAndRegcode(User recipient, String regcode);

    public List<Parcel> getAllSentUserParcelsThatHaveBeenReceived(User mailer);

    public List<Parcel> getAllTravelingUserParcels(User mailer);

    public List<Parcel> getAllParcelsSentByUser(User user);

    public List<Parcel> getAllParcelsReceivedByUser(User user);

    public int getTotalCountOfParcels();

    public int getTotalCountOfReceivedParcels();

    public void searchForLostParcelsAndMarkThemAsLost();
}
