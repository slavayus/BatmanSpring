package batman;

public class BatmanParams {
    private int x;
    private int y;
    private int zoom;

    public BatmanParams(int x, int y, int zoom) {
        this.x = x;
        this.y = y;
        this.zoom = zoom;
    }

    public BatmanParams() {
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getZoom() {
        return zoom;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }
}
