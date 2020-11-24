package scheduledTask;

import dao.IParcelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.IParcelService;

@Component
public class searchForLostUserParcelsAndMarkThemAsLost {

    private IParcelService parcelService;

    @Autowired
    public searchForLostUserParcelsAndMarkThemAsLost(IParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @Scheduled(initialDelay = 20000, fixedDelay =86400000)//
    public void searchForLostUserParcelsAndMarkThemAsLost(){
        parcelService.searchForLostParcelsAndMarkThemAsLost();
    }
}
