package batman;

import batman.logic.CheckBatman;
import database.entity.PointEntity;
import database.entity.UserEntity;
import database.repository.PointRepository;
import database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@SessionAttributes(types = UserSession.class)
public class BatmanController {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckBatman checkBatman;

    @ModelAttribute("user")
    public UserSession createUser() {
        return new UserSession();
    }

    @Autowired
    private ModelAndView modelAndView;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserSession registerUser(HttpSession httpSession, @RequestBody UserParams userParams) {
        UserEntity userEntity = new UserEntity(userParams.getLogin(), userParams.getPasswordHash(), userParams.getHashCode());
        if ((userParams.getHashCode() != null) && (userParams.getLogin() != null) && (userParams.getPasswordHash() != null) && (userRepository.findByLogin(userEntity.getLogin()) == null)) {
            userRepository.save(userEntity);

            UserSession userSession = new UserSession(userEntity.getId(), userEntity.getLogin());
            modelAndView.addObject(userSession);

            return userSession;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserSession checkUser(HttpSession httpSession, @RequestBody UserParams userParams) {
        UserEntity userEntity = new UserEntity(userParams.getLogin(), userParams.getPasswordHash(), userParams.getHashCode());
        if (userEntity.getLogin() != null) {
            UserEntity byLogin = userRepository.findByLogin(userEntity.getLogin());

            if ((byLogin != null) && (byLogin.getPasswordHash().equals(userEntity.getPasswordHash()))) {
                UserSession userSession = new UserSession(byLogin.getId(), byLogin.getLogin());
                modelAndView.addObject(userSession);
                return userSession;
            }
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/check")
    public PointEntity checkBatman(@RequestBody BatmanParams batmanParams) {
        UserSession userSession = ((UserSession) modelAndView.getModel().get("userSession"));
        if ((userSession != null) && (userRepository.findByIdAndLogin(userSession.getId(), userSession.getLogin()) != null)) {
            this.checkBatman.setGetX(batmanParams.getX());
            this.checkBatman.setGetY(batmanParams.getY());
            this.checkBatman.setGetZoom(batmanParams.getZoom());
            PointEntity pointEntity = this.checkBatman.updatePoint();
            pointRepository.save(pointEntity);
            return pointEntity;
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/results-by-zoom")
    public List<PointEntity> getResultsByZoom
            (@RequestParam(value = "zoom", required = false, defaultValue = "0") Double zoom) {
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

    @Bean
    public ModelAndView getModelAndView() {
        return new ModelAndView("/yee");
    }
}
