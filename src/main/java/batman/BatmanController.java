package batman;

import batman.logic.CheckBatman;
import database.entity.PointEntity;
import database.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BatmanController {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private CheckBatman checkBatman;

    @RequestMapping(method = RequestMethod.POST, value = "/user/check")
    public PointEntity checkBatman(@RequestBody BatmanParams batmanParams) {
        this.checkBatman.setGetX(batmanParams.getX());
        this.checkBatman.setGetY(batmanParams.getY());
        this.checkBatman.setGetZoom(batmanParams.getZoom());
        PointEntity pointEntity = this.checkBatman.updatePoint();
        pointRepository.save(pointEntity);
        return pointEntity;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/results-by-zoom")
    public List<PointEntity> getResultsByZoom(@RequestParam(value = "zoom", required = false, defaultValue = "0") Double zoom) {
        return pointRepository.findByZoom(zoom);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/results")
    public List<PointEntity> getResults() {
        List<PointEntity> pointEntitiesList = new ArrayList<>();
        pointRepository.findAll().forEach(pointEntitiesList::add);
        return pointEntitiesList;
    }

    @Bean
    public CheckBatman getCheckBatman() {
        return new CheckBatman();
    }
}
