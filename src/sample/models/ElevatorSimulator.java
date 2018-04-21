package sample.models;

import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Elevator Simulation
 *
 * @author kerlin
 */
public class ElevatorSimulator {



    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Setup Building
        PriorityQueue<Rider>[] floors = new PriorityQueue[5];
        for (int i = 0; i < floors.length; i++)
            floors[i] = new PriorityQueue();

        //Setup riders
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

        //Setup Elevator
        Elevator elevator = new Elevator(5);//can hold to 5 people..

        //Test code to make sure that the riders worked!
        /*
        for (Rider rider: riders)
            System.out.println(rider);
        */

        //Morning Mode, going to work at different floors at a company from ground
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
        //wer


        //Output
        int result = 0;
        int resultVIP = 0;
        int resultNoVIP = 0;
        for (Rider rider : riders) {
            result += rider.getFrustration();
            if (rider.isVIP())
                resultVIP += rider.getFrustration();
            else
                resultNoVIP += rider.getFrustration();
        }

        System.out.println("AM MODE:\n\tAverage (MEAN) Frustration Level is: " + ((double) result / riders.size()));
        System.out.println("AM MODE:\n\tAverage (MEAN) VIP Frustration Level is: " + ((double) resultVIP / (riders.size() / 10)));
        System.out.println("AM MODE:\n\tAverage (MEAN) Non-VIP Frustration Level is: " + ((double) resultNoVIP / (riders.size() * .9)));

//
        //Evening Mode
        //Reset Starting Conditions
        riderCount = 0;
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
            //System.out.println(building[1]);
        }

        //Output
        result = 0;
        resultVIP = 0;
        resultNoVIP = 0;
        for (Rider rider : riders) {
            result += rider.getFrustration();
            if (rider.isVIP())
                resultVIP += rider.getFrustration();
            else
                resultNoVIP += rider.getFrustration();
        }

        System.out.println("PM MODE:\n\tAverage (MEAN) Total Frustration Level is: " + ((double) result / riders.size()));
        System.out.println("PM MODE:\n\tAverage (MEAN) VIP Frustration Level is: " + ((double) resultVIP / (riders.size() / 10)));
        System.out.println("PM MODE:\n\tAverage (MEAN) Non-VIP Frustration Level is: " + ((double) resultNoVIP / (riders.size() * .9)));

    }

    public static void addRiders(int quantity, int floor, ArrayList<Rider> list) {
        for (int i = 0; i < quantity; i++)
            list.add(new Rider(floor));
    }

}
