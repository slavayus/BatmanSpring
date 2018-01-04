package database.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "point")
public class PointEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double x;
    private double y;
    private double zoom;
    private boolean inBatman;
    @Column(name = "curr_time", columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date currentTime;
    private long processTime;
    @ManyToOne
    private UserEntity userEntity;

    public PointEntity(double x, double y, double zoom, boolean inBatman, Date currentTime, long processTime) {
        this.x = x;
        this.y = y;
        this.zoom = zoom;
        this.inBatman = inBatman;
        this.currentTime = currentTime;
        this.processTime = processTime;
    }

    public PointEntity() {
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public void setInBatman(boolean inBatman) {
        this.inBatman = inBatman;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public void setProcessTime(long processTime) {
        this.processTime = processTime;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZoom() {
        return zoom;
    }

    public boolean isInBatman() {
        return inBatman;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public long getProcessTime() {
        return processTime;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    @Override
    public String toString() {
        return "PointEntity(" +
                "id = " + id +
                "  x = " + x +
                "  y = " + y +
                "  zoom = " + zoom +
                "  Strike = " + inBatman +
                "  Time = " + currentTime +
                "  ProgramLiveTime = " + currentTime +
                ");";
    }
}
