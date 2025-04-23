import java.util.concurrent.*;

public class RunwayManager 
{
    private final Semaphore towerClearance;
    private final Semaphore[] runways;

    public RunwayManager(int numberOfRunways) 
    {
        this.towerClearance = new Semaphore(numberOfRunways, true);
        this.runways = new Semaphore[numberOfRunways];
        for (int i = 0; i < numberOfRunways; i++) {
            runways[i] = new Semaphore(1); 
        }
    }

    public int requestRunway(int planeId) throws InterruptedException 
    {
        System.out.println("Plane " + planeId + " is requesting clearance from control tower.");
        towerClearance.acquire();

        int assignedRunway = -1;

        while (true) 
        {
            System.out.println("Plane " + planeId + " has not been assigned a runway yet.");
            for (int i = 0; i < runways.length; i++) 
            {
                if (runways[i].tryAcquire()) 
                {
                    assignedRunway = i;
                    break;
                }
            }
            if (assignedRunway != -1) 
            {
                break;
                
            }
        }

        System.out.println("Plane " + planeId + " granted access to Runway " + assignedRunway + ".");
        return assignedRunway;
    }

    public void releaseRunway(int runwayIndex, int planeId) throws InterruptedException
    {
        runways[runwayIndex].release();
        towerClearance.release();
        System.out.println("Plane " + planeId + " has released Runway " + runwayIndex + ".");
    }
}

