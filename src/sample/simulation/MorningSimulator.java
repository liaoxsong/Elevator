package sample.simulation;

import sample.models.Elevator;
import sample.models.FloorFactory;
import sample.models.Rider;
import sample.models.RiderFactory;

import java.util.*;

/**
 * Created by song on 2018/4/19.
 */
public class MorningSimulator {


    private static List<Rider> riders = RiderFactory.createAllRiders();

    private static PriorityQueue<Rider>[] floors = FloorFactory.createFloors(5);
    
    public static void main(String []args) {
        Elevator elevator = new Elevator(5);
        int riderCount = 0;

        int peopleAtATime = 5;

        while (riderCount < riders.size() || elevator.peek() != null || floors[0].peek() != null) {
            //New rider!
            List<Rider> newRiders = new ArrayList<>();

            for (int index = 0; index < peopleAtATime; index++) {
                int count = riderCount++;
                if (count < riders.size()){
                    newRiders.add(riders.get(count));
                }
            }

            Collections.sort(newRiders);
            floors[0].addAll(newRiders);

            //Pop riders for elevator's current floor
            while (elevator.peek() != null && elevator.peek().getHomeFloor() == elevator.getCurrentFloor()) {
                floors[elevator.getCurrentFloor() - 1].add(elevator.pop());
            }

            //System.out.println("AFTER POPS" + elevator);

            //Frustrate
            elevator.frustrate();

            //Push riders from current floor to elevator, no one has ground floor as homefloor though
            while (elevator.getCurrentFloor() == 1 &&
                    !elevator.isFull() &&
                    !floors[elevator.getCurrentFloor() - 1].isEmpty())

                elevator.push(floors[elevator.getCurrentFloor() - 1].remove());

            //System.out.println("ELEVATOR BEFORE MOVE: " + elevator);

            //Move elevator
            if (elevator.peek() != null)
                elevator.setCurrentFloor(elevator.peek().getHomeFloor());
            else
                elevator.setCurrentFloor(1);

            System.out.println("ELEVATOR AT END: " + elevator);
        }
    }
}
