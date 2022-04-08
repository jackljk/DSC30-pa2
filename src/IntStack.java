/*
    Name: Jack Kai Lim
    PID:  A16919063
 */

import java.util.EmptyStackException;

/**
 * Integer Resizing Stack Class
 * @author Jack Kai Lim
 * @since  04/05/2022
 */
public class IntStack {

    /* instance variables, feel free to add more if you need */
    private int[] data; /* To store the elements */
    private int nElems; /* Keep track of size of stack */
    private double loadFactor; /* Values to indicate whether the array needs to be resized */
    private double shrinkFactor; /* Values to indicate whether the array needs to be resized */
    private int initCapacity; /* Stores initial capacity. */

    /**
     * Constructor
     * @param capacity How much the array can hold at max
     * @param loadF Proportion which the size of the stack needs to be increased
     * @param shrinkF Proportion which the size of the stack needs to be shrunk
     */
    public IntStack(int capacity, double loadF, double shrinkF) {
        /* Constructor when the capacity, loadF and shrinkF are all specified */
        this.nElems = -1; /* Start nelems of a stack as arrays start from 0 */
        if (capacity < 5){
            /* When capacity is less than 5, it is an illegal argument*/
            throw new IllegalArgumentException();
        } else {
            this.data = new int[capacity];
            this.initCapacity = capacity;
        }
        if (loadF > 1 || loadF < 0.67){
            /* When loadF is not in valid range throws illegal argument exception */
            throw new IllegalArgumentException();
        } else {
            this.loadFactor = loadF;
        }
        if (shrinkF <= 0 || shrinkF > 0.33) {
            /* When shrinkF is not in valid range throws illegal argument exception */
            throw new IllegalArgumentException();
        } else {
            this.shrinkFactor = shrinkF;
        }
    }

    /**
     * Constructor without shrinkF
     * @param capacity How much the array can hold at max
     * @param loadF Proportion which the size of the stack needs to be increased.
     */
    public IntStack(int capacity, double loadF) {
        /* Constructor method when only the capacity and loadF are specified */
        this.nElems = -1; /* Start nelems of a stack as arrays start from 0 */
        if (capacity < 5) {
            /* When capacity is less than 5, it is an illegal argument*/
            throw new IllegalArgumentException();
        } else {
            this.data = new int[capacity];
            this.initCapacity = capacity;
        }
        if (loadF > 1 || loadF < 0.67) {
            /* When loadF is not in valid range throws illegal argument exception */
            throw new IllegalArgumentException();
        } else {
            this.loadFactor = loadF;
        }
    }

    /**
     * Constructor without loadF or shrinkF
     * @param capacity How much the stack can hold at max.
     */
    public IntStack(int capacity) {
        /* Constructor method when only capacity is given. */
        this.nElems = -1; /* Start nelems of a stack as arrays start from 0 */
        if (capacity < 5) {
            /* When capacity is less than 5, it is an illegal argument*/
            throw new IllegalArgumentException();
        } else {
            this.data = new int[capacity];
            this.initCapacity = capacity;
        }
    }

    /**
     * Constructor of default Values of stack
     */
    public IntStack(){
        this(5, 0.75, 0.25);
    }

    /**
     * Method
     * @return True if empty and False otherwise
     */
    public boolean isEmpty() {
        /* Returns true if stack is empty, returns false otherwise */
        return this.nElems == -1;
    }

    /**
     * Method
     */
    public void clear() {
        /* Clears the Stack and resets it to it's initial capacity. */
        this.data = new int[this.initCapacity];
        this.nElems = -1;
    }

    /**
     * Getter Method
     * @return Number of elements in the stack
     */
    public int size() {
        /* Returns the number of elements in the stack */
        return this.nElems + 1;
    }

    /**
     * Getter Method
     * @return Total capacity of stack
     */
    public int capacity() {
        /* Returns the total capacity of the stack at the moment */
        return this.data.length;
    }

    /**
     * Getter Method
     * @return The value that is at the top of the stack
     */
    public int peek() {
        /* Returns the element on the top of the stack */
        if (isEmpty()){
            throw new EmptyStackException(); /* Throws this exception if stack is empty */
        }
    return this.data[this.nElems];
    }

    /**
     * Method
     * @param element element to be added to the top of the stack
     */
    public void push(int element) {
        /* Adds a new element to the stack, doubles its size if the load factor is reached */
        double num = this.size();
        double denom = this.capacity();
        double prop = num / denom;
        if (prop >= this.loadFactor){
            int[] temp_arr = new int[this.capacity() * 2];
            if (this.size() >= 0) {
                /* Makes a copy of the old array onto a new and larger one */
                System.arraycopy(this.data, 0, temp_arr, 0, this.size());
            }
            /* Replaces the main array with the new and larger array */
            this.data = temp_arr;
            /* Adds the new element to the top of the stack */
            this.nElems += 1;
            this.data[nElems] = element;
        } else {
            /* Adds the new element to the top of the stack */
            this.nElems += 1;
            this.data[this.nElems] = element;
        }
    }

    /**
     * Method
     * @return Element that was popped.
     */
    public int pop() {
        /* Removes the element at the top of the stack and returns the element. Also, when the
        shrink Factor is reached floor halves the capacity. */
        int temp; /* Temp int to return after removing */
        double num = this.size();
        double denom = this.capacity();
        double prop = num / denom;
        if (isEmpty()){
            /* Throws an exception if the stack is empty */
            throw new EmptyStackException();
        } else {
            /* Pops the element at the top of the stack */
            temp = this.data[this.nElems];
            this.data[this.nElems] = 0;
            this.nElems -= 1;
        }
        if (prop <= this.shrinkFactor){
            int size;
            int base = this.capacity() / 2;
            if (base <= 5){
                size = 5;
            } else {
                size = base;
            }
            int[] temp_arr = new int[size];
            if (nElems >= 0){
                System.arraycopy(this.data, 0, temp_arr, 0, nElems);
            }
            this.data = temp_arr;
        }
        return temp;
    }

    /**
     * Method
     * @param elements array of elements to be added to the stack
     */
    public void multiPush(int[] elements) {
        /* Iterates through the elements array and pushes each individual element in order. */
        if (elements == null){
            /* Throws illegal argument exception if elements is null/not an array */
            throw new IllegalArgumentException();
        } else {
            /* Iterates through the whole array and pushing each element into the stack in
            order. */
            for (int element : elements) {
                this.push(element);
            }
        }
    }

    /**
     * Method
     * @param amount Number of elements to pop from stack
     * @return Array of the elements popped.
     */
    public int[] multiPop(int amount) {
        /* Pops multiple values from the stack and returns an array. */
        if (amount < 0){
            /* Throws illegal argument exception if the amount is not a positive number. */
            throw new IllegalArgumentException();
        }
        /* Pops and adds to an array the number of specified times. */
        int[] arr = new int[amount];
        for (int i = 0; i < amount; i++){
            arr[i] = this.pop();
        }
        return arr;
    }
}
