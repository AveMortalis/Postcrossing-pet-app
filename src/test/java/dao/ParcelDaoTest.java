package dao;

import config.TestConfig;
import entity.Parcel;
import entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
@Transactional
class ParcelDaoTest {

    @Autowired
    IParcelDao parcelDao;

    @Test
    @Sql({"classpath:data.sql"})
    void getAll() {
        List<Parcel> list=parcelDao.getAll();
        Assertions.assertEquals(15,list.size());
    }

    @Test
    @Sql({"classpath:data.sql"})
    void getParcelThatExistsById() {
        Parcel parcelByID = parcelDao.getParcelById(22);
        Assertions.assertNotNull(parcelByID);
        Assertions.assertEquals(22,parcelByID.getId());
    }

    @Test
    @Sql({"classpath:data.sql"})
    void deleteParcelThatExists() {
        Parcel parcelForDelete = parcelDao.getParcelById(22);
        parcelDao.delete(parcelForDelete);
        Parcel parcelAfterDeletion = parcelDao.getParcelById(22);
        Assertions.assertNotNull(parcelForDelete);
        Assertions.assertNull(parcelAfterDeletion);
    }

    @Test
    @Sql({"classpath:data.sql"})
    void add() {
        User mailer=new User();
        mailer.setId(2);
        User recipient = new User();
        recipient.setId(3);
        Parcel newParcel = new Parcel();
        newParcel.setRegistrationCode("TESTREGCODE");
        newParcel.setMailer(mailer);
        newParcel.setRecipient(recipient);
        newParcel.setStatus("Sent");

        parcelDao.add(newParcel);

        Assertions.assertNotNull(parcelDao.getParcelByRecipientAndRegcode(recipient,"TESTREGCODE"));
    }

    @Test
    @Sql({"classpath:data.sql"})
    void update() {
        Parcel parcelForUpdate = parcelDao.getParcelById(22);
        parcelForUpdate.setRegistrationCode("NEWREGCODE");
        parcelDao.update(parcelForUpdate);
        Parcel parcelAfterDeletion = parcelDao.getParcelById(22);
        Assertions.assertNotNull(parcelForUpdate);
        Assertions.assertEquals("NEWREGCODE",parcelDao.getParcelById(22).getRegistrationCode());
    }

    @Test
    @Sql({"classpath:data.sql"})
    void updateParcelStatus() {
        Parcel parcelForUpdate = parcelDao.getParcelById(22);
        parcelForUpdate.setStatus("Lost");
        parcelDao.update(parcelForUpdate);
        Parcel parcelAfterDeletion = parcelDao.getParcelById(22);
        Assertions.assertNotNull(parcelForUpdate);
        Assertions.assertEquals("Lost",parcelDao.getParcelById(22).getStatus());
    }

    @Test
    @Sql({"classpath:data.sql"})
    void getParcelByRecipientAndRegcode() {
        User recipient = new User();
        recipient.setId(7);
        String regcode = "AF443227AO";

        Parcel parcel= parcelDao.getParcelByRecipientAndRegcode(recipient, regcode);

        Assertions.assertEquals(6,parcel.getMailer().getId());

    }

    @Test
    @Sql({"classpath:data.sql"})
    void getAllSentUserParcelsHaveReceived() {
        User mailer = new User();
        mailer.setId(6);
        int countOfReceived=4;

        List<Parcel> parcels=parcelDao.getAllSentUserParcelsThatHaveBeenReceived(mailer);
        Assertions.assertEquals(countOfReceived,parcels.size());

    }

    @Test
    @Sql({"classpath:data.sql"})
    void getAllTravelingUserParcels() {
        User mailer = new User();
        mailer.setId(6);
        int countOfTraveling=2;

        List<Parcel> parcels=parcelDao.getAllTravelingUserParcels(mailer);
        Assertions.assertEquals(countOfTraveling,parcels.size());
    }

    @Test
    @Sql({"classpath:data.sql"})
    void getAllSentUserParcels() {
        User mailer = new User();
        mailer.setId(6);
        int countOfSent=7;

        List<Parcel> parcels=parcelDao.getAllParcelsSentByUser(mailer);
        Assertions.assertEquals(countOfSent,parcels.size());
    }

    @Test
    @Sql({"classpath:data.sql"})
    void getAllReceivedParcelsByUser() {
        User user = new User();
        user.setId(4);
        int countOfReceivedByUser=3;

        List<Parcel> parcels=parcelDao.getAllParcelsReceivedByUser(user);
        Assertions.assertEquals(countOfReceivedByUser,parcels.size());
    }

    @Test
    @Sql({"classpath:data.sql"})
    void getCountOfParcels() {
        int countOfParcels=parcelDao.getTotalCountOfParcels();

        Assertions.assertEquals(15,countOfParcels);

    }

    @Test
    @Sql({"classpath:data.sql"})
    void getCountOfReceivedParcels() {
        int countOfParcelsReceived=parcelDao.getTotalCountOfReceivedParcels();

        Assertions.assertEquals(12,countOfParcelsReceived);
    }
}