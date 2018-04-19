package sample.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by song on 2018/4/19.
 */
public class RiderFactory {

    public static List<Rider> createAllRiders(){
        ArrayList<Rider> riders = new ArrayList();
        addRiders(20, 5, riders);
        addRiders(10, 4, riders);
        addRiders(8, 3, riders);
        addRiders(15, 2, riders);
        Collections.shuffle(riders);

        //Make VIPs
        for (int i = 0; i < riders.size() / 5; i++) {
            riders.get(i).promote();
        }

        Collections.shuffle(riders);

        return riders;
    }

    public static void addRiders(int quantity, int floor, ArrayList<Rider> list) {
        for (int i = 0; i < quantity; i++)
            list.add(new Rider(floor));
    }

}
