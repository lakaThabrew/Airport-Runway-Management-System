import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

