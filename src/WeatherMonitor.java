/*
    Name: Jack Kai Lim
    PID:  A16919063
 */

/**
 * TODO
 * @author TODO
 * @since  TODO
 */

public class WeatherMonitor {
    private IntStack monitor;
    private int counter;
    public WeatherMonitor() {
        /* TODO */
        this.monitor = new IntStack(9);
    }
    
    public int numDays(int temp) {
       /* TODO */
        if (this.monitor.size() == 0){
            this.monitor.push(temp);
            return 0;
        } else {
            int pre_day = this.monitor.peek();
            this.monitor.push(temp);
            if (pre_day < temp){
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