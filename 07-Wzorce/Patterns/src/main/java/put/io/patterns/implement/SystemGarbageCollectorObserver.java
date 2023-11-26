package put.io.patterns.implement;

public class SystemGarbageCollectorObserver implements SystemStateObserver{
    private SystemState lastSystemState = null;

    @Override
    public void update(SystemMonitor monitor) {
        lastSystemState = monitor.getLastSystemState();
        collectGarbage();
    }

    public void collectGarbage() {
        // Run garbage collector when out of memory
        if (lastSystemState.getAvailableMemory() < 200.00){
            System.out.println("> Running garbage collector...");
        }
    }
}
