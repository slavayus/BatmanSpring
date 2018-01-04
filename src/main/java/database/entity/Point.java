package database.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double x;
    private double y;
    private double zoom;
    private boolean inBatman;
    @Column(name = "curr_time", columnDefinition="TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date currTime;
    private long processTime;

    public Point(double x, double y, double zoom, boolean inBatman, Date currTime, long processTime) {
        this.x = x;
        this.y = y;
        this.zoom = zoom;
        this.inBatman = inBatman;
        this.currTime = currTime;
        this.processTime = processTime;
    }

    public Point() {
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

    public void setCurrTime(Date currTime) {
        this.currTime = currTime;
    }

    public void setProcessTime(long processTime) {
        this.processTime = processTime;
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

    public Date getCurrTime() {
        return currTime;
    }

    public long getProcessTime() {
        return processTime;
    }


    @Override
    public String toString() {
        return "Point(" +
                "id = " + id +
                "  x = " + x +
                "  y = " + y +
                "  zoom = " + zoom +
                "  Strike = " + inBatman +
                "  Time = " + currTime +
                "  ProgramLiveTime = " + currTime +
                ");";
    }
}
