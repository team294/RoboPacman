package pacman.base;


public class GhostSensor {

    private int angle = 0;
    private int ping = 0;
    private int radar = 0;

    public int getPing() {
        return ping;
    }

    public int getRadar() {
        return radar;
    }

    public void setRadar(int radar) {
        this.radar = radar;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

}