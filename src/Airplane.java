import java.util.Random;

class Airplane implements Runnable 
{
    private final int planeId;
    private final RunwayManager runwayManager;

    public Airplane(int planeId, RunwayManager runwayManager) 
    {
        this.planeId = planeId;
        this.runwayManager = runwayManager;
    }

    @Override
    public void run() 
    {
        try 
        {
            int runwayIndex = runwayManager.requestRunway(planeId);
            System.out.println("Plane " + planeId + " is using Runway " + runwayIndex + ".");
            Thread.sleep(new Random().nextInt(1000) + 1000); 
            runwayManager.releaseRunway(runwayIndex, planeId);
        } 
        catch (InterruptedException e) 
        {
            Thread.currentThread().interrupt();
            System.err.println("Plane " + planeId + " was interrupted.");
        }
    }
}