import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Start {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Start.class);
        RobotT1000 robotT1000 = (RobotT1000) applicationContext.getBean("t1000");
        robotT1000.dance();
    }

    @Bean("t1000")
    public Robot getRobot() {
        return new RobotT1000();
    }
}
