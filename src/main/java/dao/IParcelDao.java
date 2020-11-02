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

    public List<Parcel> getAllSentUserParcelsHaveReceived(User mailer);

    public List<Parcel> getAllTravelingUserParcels(User mailer);

    public List<Parcel> getAllSentUserParcels(User user);

    public List<Parcel> getAllReceivedParcelsByUser(User user);

    public int getCountOfParcels();

    public int getCountOfReceivedParcels();
}
