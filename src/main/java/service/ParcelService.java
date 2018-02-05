package service;

import dao.ParcelDao;
import entity.Parcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParcelService {

    private ParcelDao parcelDao;

    @Autowired
    public ParcelService(ParcelDao parcelDao) {
        this.parcelDao = parcelDao;
    }

    @Transactional
    public List<Parcel> getAll(){
        return parcelDao.getAll();
    }

}
