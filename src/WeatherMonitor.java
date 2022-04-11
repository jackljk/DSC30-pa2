/*
    Name: Jack Kai Lim
    PID:  A16919063
 */

import java.util.Arrays;

/**
 * Weather Monitor implementation
 * @author Jack Kai Lim
 * @since  04/06/2022
 */

public class WeatherMonitor {
    private IntStack monitor;
    public WeatherMonitor() {
        /* Initializing Weather Monitor as a stack */
        int BASE_CAPACITY = 10;
        this.monitor = new IntStack(BASE_CAPACITY);
    }

    /**
     * Counts the number of previous days which the temp is less than the temp of that day
     * @param temp the temperature of that day
     * @return No of days less than.
     */
    public int numDays(int temp) {
        /* Counts the number of previous days which the temp is less than the current day. */
        int counter = 0;
       if (monitor.size() == 0){
           monitor.push(temp);
           return 0;
       } else {
           int[] popped_values = monitor.multiPop(monitor.size());
           for (int i = 0;i<popped_values.length;i++){
               /* Iterates through the array and counts the number of days which is less than the
               *  current days temp. */
               if (popped_values[i] < temp){
                   counter += 1;
               } else {
                   break;
               }
           }
           for (int i = popped_values.length - 1;i>=0;i--){
               /* Pushes all popped values back into the stack */
               monitor.push(popped_values[i]);
           }
           monitor.push(temp);
       }
    return counter;
    }
}
