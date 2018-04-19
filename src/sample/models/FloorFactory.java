package sample.models;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by song on 2018/4/19.
 */
public class FloorFactory {

    public int floorCount;

    public static PriorityQueue<Rider>[] createFloors(int floorCount) {
        PriorityQueue<Rider> []floors = new PriorityQueue[floorCount];
        for(int i=0;i<floorCount;i++) {
            floors[i] = new PriorityQueue<>();
        }
        return floors;
    }
}
