package sample.models;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * A person who rides an elevator
 *
 * @author kerlin
 */
public class Rider implements Comparable
{

    private static int counter = 0;
    private int id;
    private int frustration = 0;
    private int homeFloor;
    private boolean vip = false;

    public boolean isVIP()
    {
        return vip;
    }
    
    public void promote()
    {
        vip = true;
    }
    
   
    @Override
    public int compareTo(Object t)
    {
        if (! (t instanceof Rider))
        {return 0;}
        else
        {
            if (isVIP() == ((Rider) t).isVIP())
            {
                return 0;
            }
            else if (isVIP())
            {
                return -1;
            }
            else 
            {
                return 1;
            }
        }
    }

    /**
     * Get the value of homeFloor
     *
     * @return the value of homeFloor
     */
    public int getHomeFloor()
    {
        return homeFloor;
    }

    /**
     * Set the value of homeFloor
     *
     * @param homeFloor new value of homeFloor
     */
    public void setHomeFloor(int homeFloor)
    {
        this.homeFloor = homeFloor;
    }


    public Rider()
    {
        id = ++counter;
    }
    
    public Rider(int floor)
    {
        id = ++counter;
       
        homeFloor = floor;
    }

    public Rider(int floor, boolean isVIP)
    {
        id = ++counter;
       
        homeFloor = floor;
        
        vip = isVIP;
    }

    
    public int getFrustration()
    {
        return frustration;
    }

    public void frustrate() {
        this.frustration ++;
    }

    public void clearFrustration()
    {
        this.frustration = 0;
    }

    public int getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return "Rider{" + "id=" + id + ", frustration=" + frustration + ", homeFloor=" + homeFloor + ", vip=" + vip + '}';
    }


}
