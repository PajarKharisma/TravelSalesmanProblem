package algorithm;

import java.awt.*;

public class Ecludian {

    public int getDistance(Point pos1, Point pos2){
        double x = Math.pow((pos1.x - pos2.x),2);
        double y = Math.pow((pos1.y - pos2.y),2);
        int result = (int)Math.ceil(Math.sqrt(x + y));
        return result;
    }
}
