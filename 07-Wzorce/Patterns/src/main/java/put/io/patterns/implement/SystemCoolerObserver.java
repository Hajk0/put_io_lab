package put.io.patterns.implement;

public class SystemCoolerObserver implements SystemStateObserver{
    private SystemState lastSystemState = null;
    @Override
    public void update(SystemMonitor monitor) {
        lastSystemState = monitor.getLastSystemState();
        cool();
    }

    public void cool() {
        // Increase CPU cooling if the temperature is too high
        if (lastSystemState.getCpuTemp() > 60.00){
            System.out.println("> Increasing cooling of the CPU...");
        }
    }
}
