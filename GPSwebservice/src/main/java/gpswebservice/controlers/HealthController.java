package gpswebservice.controlers;

import gpswebservice.ioc.Ioc;
import gpswebservice.model.Status;
import gpswebservice.storage.StatusStorage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private StatusStorage statusStorage;

    public HealthController() {
        this.statusStorage = Ioc.getInstance().getStatusStorage();
    }

    @RequestMapping("/status")
    public Status getStatus() {
        return statusStorage.getStatus();
    }
}
