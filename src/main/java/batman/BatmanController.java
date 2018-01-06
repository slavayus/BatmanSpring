package batman;

import batman.logic.CheckBatman;
import database.entity.PointEntity;
import database.entity.UserEntity;
import database.repository.PointRepository;
import database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BatmanController {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CheckBatman checkBatman;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public UserEntity registerUser(@RequestBody UserParams userParams) {
        UserEntity userEntity = new UserEntity(userParams.getLogin(), userParams.getPasswordHash(), userParams.getHashCode());
        if ((userParams.getHashCode() != null) && (userParams.getLogin() != null) && (userParams.getPasswordHash() != null) && (userRepository.findByLogin(userEntity.getLogin()) == null)) {
            userRepository.save(userEntity);
            return userEntity;
        } else {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/check")
    public PointEntity checkBatman(@RequestBody BatmanParams batmanParams) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserEntity byLogin = userRepository.findByLogin(principal.getUsername());

        if ((principal.getUsername() != null) && (byLogin != null)) {
            this.checkBatman.setGetX(batmanParams.getX());
            this.checkBatman.setGetY(batmanParams.getY());
            this.checkBatman.setGetZoom(batmanParams.getZoom());

            PointEntity pointEntity = this.checkBatman.updatePoint();
            pointEntity.setUserEntity(byLogin);

            pointRepository.save(pointEntity);
            return pointEntity;
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/results-by-zoom")
    public List<PointEntity> getResultsByZoom
            (@RequestParam(value = "zoom", required = false, defaultValue = "0") Double zoom) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserEntity byLogin = userRepository.findByLogin(principal.getUsername());

        return pointRepository.findByUserEntityAndZoom(byLogin, zoom);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/results")
    public List<PointEntity> getResults() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity byLogin = userRepository.findByLogin(principal.getUsername());
        return pointRepository.findByUserEntityId(byLogin.getId());
    }

    @Bean
    public CheckBatman getCheckBatman() {
        return new CheckBatman();
    }

}
