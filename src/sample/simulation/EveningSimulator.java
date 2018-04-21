package sample.simulation;

import sample.models.Elevator;
import sample.models.FloorFactory;
import sample.models.Rider;
import sample.models.RiderFactory;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by song on 2018/4/19.
 */
public class EveningSimulator {
    private static List<Rider> riders = RiderFactory.createAllRiders();

    private static PriorityQueue<Rider>[] floors = FloorFactory.createFloors(5);

    public static void main(String[] args) {
        Elevator elevator = new Elevator(5);

        int riderCount = 0;
        int peopleAtATime = 5;

        for (int i = 0; i < riders.size(); i++)
            riders.get(i).clearFrustration();//clear frustration
        for (int i = 0; i < floors.length; i++)
            while (!floors[i].isEmpty())
                floors[i].remove();//clear riders from all floors
        while (riderCount < riders.size() ||
                elevator.peek() != null ||
                floors[1].peek() != null ||
                floors[2].peek() != null ||
                floors[3].peek() != null ||
                floors[4].peek() != null) {


            //New rider!
            if (riderCount < riders.size()) {
                //10 new riders

                for (int index = 0; index < peopleAtATime; index++) {
                    //add rider from their home floor
                    Rider thisRider = riders.get(riderCount);

                    floors[thisRider.getHomeFloor() - 1].add(thisRider);
                    riders.get(riderCount).setHomeFloor(1);//all want to go home
                    riderCount++;
                }
            }


            //Pop riders for elevator's current floor
            while (elevator.peek() != null && elevator.peek().getHomeFloor() == elevator.getCurrentFloor()) {
                floors[elevator.getCurrentFloor() - 1].add(elevator.pop());
            }

            //System.out.println("AFTER POPS" + elevator);

            //Frustrate
            elevator.frustrate();


            //Push riders from current floor to elevator
            while (elevator.getCurrentFloor() != 1 && !elevator.isFull() && !floors[elevator.getCurrentFloor() - 1].isEmpty())
                elevator.push(floors[elevator.getCurrentFloor() - 1].remove());

            //System.out.println("ELEVATOR BEFORE MOVE: " + elevator);


            //Move elevator
            //todo: should move down instead of random
            Random gen = new Random();
            if (elevator.peek() != null)
                elevator.setCurrentFloor(elevator.peek().getHomeFloor());
            else
                elevator.setCurrentFloor(gen.nextInt(4) + 2);

            System.out.println("ELEVATOR AT END: " + elevator);
        }
    }
}
