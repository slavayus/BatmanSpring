package hello;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import database.entity.Point;
import database.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private PointRepository pointRepository;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Point greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        Point point = new Point(2.3, 4.5, 6.7, false, new Date(), 234234L);
        pointRepository.save(point);
        return point;
//        return new Greeting(counter.incrementAndGet(), String.format(template, name), pointRepository);
    }


}
