package hello;

import database.repository.PointRepository;

public class Greeting {

    private final long id;
    private final String content;
    private final Boolean inBatman;

    Greeting(long id, String content, PointRepository pointRepository) {
        this.id = id;
        this.content = content;
        this.inBatman = pointRepository.findOne(1L).isInBatman();
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Boolean getInBatman() {
        return inBatman;
    }
}