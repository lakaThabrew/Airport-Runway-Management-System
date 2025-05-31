import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
* Airport Runway Management System
*
* This program simulates an airport with multiple airplanes trying to land or take off
* using a limited number of runways. The system ensures that:
* - Only one plane can use a runway at a time
* - Planes don't wait forever (no starvation)
* - Deadlocks are avoided
*
* The solution is based on the Dining Philosophers problem, where runways represent forks
* and airplanes represent philosophers.
*/
public class AirportRunwayManagerSystem 
{
    public static void main(String[] args) 
    {
        int No_runways = 5;
        int No_airplanes = 10;

        RunwayManager runwayManager = new RunwayManager(No_runways);
        // Create a thread pool with a fixed number of threads equal to the number of airplanes
        ExecutorService executor = Executors.newFixedThreadPool(No_airplanes);

        for (int i = 0; i < No_airplanes; i++) 
        {
            executor.execute(new Airplane(i, runwayManager));
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
            // Wait until all planes finish
        }

        System.out.println("Operation is completed. All 10 airplanes have used the runway.");
    }
}

