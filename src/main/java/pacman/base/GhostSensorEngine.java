package pacman.base;

import pacman.graphics.Ghost;

import java.util.List;

public class GhostSensorEngine {

    public static int MAX_RANGE = 100;

    public int getPing(int posX, int posY, int angle, List<Ghost> ghostList) {
        int range = 0;
        int ping = 0;

        while (ping == 0 && range < MAX_RANGE) {
            range++;
            if (angle == 0 && checkForGhost(posX, posY-range, ghostList)) {
                ping = range;
            }
            if (angle == 90 && checkForGhost(posX+range, posY, ghostList)) {
                ping = range;
            }
            if (angle == 180 && checkForGhost(posX, posY+range, ghostList)) {
                ping = range;
            }
            if (angle == -90 && checkForGhost(posX-range, posY, ghostList)) {
                ping = range;
            }
        }

       //System.out.printf("getPing x:%d y:%d angle:%d ping:%d %n",posX,posY,angle,ping);

        return ping;
    }

    private boolean checkForGhost(int posX, int posY, List<Ghost> ghostList) {
        boolean found = false;
        //System.out.printf("checkForGhost at %d %d %n",posX,posY);
        for (Ghost g:ghostList) {
            //System.out.printf("checkForGhost ghost %d %d %n",g.getX(),g.getY());
            if ((g.getX() == posX) && (g.getY() ==posY)) {
                //System.out.println("found Ghost");
                found = true;
                break;
            }
        }

        return found;
    }

}