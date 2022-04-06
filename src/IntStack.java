/*
    Name: TODO
    PID:  TODO
 */

import java.util.EmptyStackException;

/**
 * TODO
 * @author TODO
 * @since  TODO
 */
public class IntStack {

    /* instance variables, feel free to add more if you need */
    private int[] data;
    private int nElems;
    private double loadFactor;
    private double shrinkFactor;

    public IntStack(int capacity, double loadF, double shrinkF) {
        /* TODO */
        try{
            if (capacity<5){
                throw new IllegalArgumentException("Capacity out of valid range");
            }
            data = new int[capacity];
            if (loadF>1 && loadF<0.67){
                throw new IllegalArgumentException("LoadF out of valid range");
            }
            loadFactor = loadF;
            if (shrinkF>0.33 && shrinkF<=0){
                throw new IllegalArgumentException("shrinkFout of valid range");
            }
            shrinkFactor = shrinkF;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public IntStack(int capacity, double loadF) {
        /* TODO */
        try {
            if (capacity < 5) {
                throw new IllegalArgumentException("Capacity out of valid range");
            }
            data = new int[capacity];
            if (loadF > 1 && loadF < 0.67) {
                throw new IllegalArgumentException("LoadF out of valid range");
            }
            loadFactor = loadF;
        }
    }

    public IntStack(int capacity) {
        /* TODO */
    }

    public boolean isEmpty() {
        /* TODO */
        return false;
    }

    public void clear() {
        /* TODO */
    }

    public int size() {
        /* TODO */
        return 0;
    }

    public int capacity() {
        /* TODO */
        return 0;
    }

    public int peek() {
        /* TODO */
        return 0;
    }

    public void push(int element) {
        /* TODO */
    }

    public int pop() {
        /* TODO */
        return 0;
    }

    public void multiPush(int[] elements) {
        /* TODO */
    }

    public int[] multiPop(int amount) {
        /* TODO */
        return null;
    }
}