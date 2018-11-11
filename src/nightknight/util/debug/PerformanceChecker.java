package nightknight.util.debug;

/**
 *
 * @author Vitor
 */
public class PerformanceChecker {
    private static long start;
    public static void startMeasurement() {
        start = System.nanoTime();
    }
    
    public static void endMeasurement(String name) {
        System.out.println(name +" finished after " +(System.nanoTime() - start) + "ms.");
    }
    
}
