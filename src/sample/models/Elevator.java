package sample.models;

import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This is the Elevator Stack
 * @author kerlin
 */
public class Elevator
{
    private Rider[] stack;
    private int riders = 0;
    private int currentFloor = 1;

    public Elevator(int capacity ) {
        stack = new Rider[capacity];
    }
    public boolean isFull()
    {
        if (riders == stack.length)
            return true;
        return false;
    }

    public int getCurrentFloor()
    {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor)
    {
        this.currentFloor = currentFloor;
    }
    
    public boolean push(Rider e)
    {
        if (riders < stack.length)
        {
            stack[riders] = e;
            riders++;
            return true;
        }
        
        return false;
    }
    
    public Rider peek()
    {
        if (riders > 0)
            return stack[riders - 1];
        return null;
    }
    
    public Rider pop()
    {
        Rider temp = peek();
        if (temp != null)
        {
            riders--;
            stack[riders] = null;
        }
        return temp;
        
    }
    
    public void frustrate()
    {
        //System.out.println(this);
        for (Rider e : stack)
        {
            if (e != null)
                e.frustrate();

        }
    }

    @Override
    public String toString()
    {
        return "Elevator{" + ", riders=" + riders + ", currentFloor=" + currentFloor + "stack=" + Arrays.toString(stack)  + '}';
    }
    
    
}
