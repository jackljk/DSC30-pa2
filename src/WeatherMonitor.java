/*
    Name: Jack Kai Lim
    PID:  A16919063
 */

/**
 * Weather Monitor implementation
 * @author Jack Kai Lim
 * @since  04/06/2022
 */

public class WeatherMonitor {
    private IntStack monitor;
    private int counter;
    public WeatherMonitor() {
        /* Initializing Weather Monitor as a stack */
        this.monitor = new IntStack(9);
    }
    
    public int numDays(int temp) {
       /* Check if the temperature on the previous day is less than today. If so increase
       counter, else reset to 0 and returns the number of days that this occurs. */
        if (this.monitor.size() == 0){
            this.monitor.push(temp);
            return 0;
        } else {
            int preDay = this.monitor.peek();
            this.monitor.push(temp);
            if (preDay < temp){
                this.counter += 1;
                return this.counter;
            } else {
                this.counter = 0;
                this.monitor.clear();
                return this.counter;
            }
        }
    }
}
