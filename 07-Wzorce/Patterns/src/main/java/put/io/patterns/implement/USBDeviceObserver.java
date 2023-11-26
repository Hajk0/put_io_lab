package put.io.patterns.implement;

public class USBDeviceObserver implements SystemStateObserver {
    private SystemState lastSystemState = null;
    private SystemState previousSystemState = null;
    @Override
    public void update(SystemMonitor monitor) {
        previousSystemState = lastSystemState;
        lastSystemState = monitor.getLastSystemState();
        reportUSBChange();
    }

    public void reportUSBChange() {
        if (previousSystemState != null && previousSystemState.getUsbDevices() != lastSystemState.getUsbDevices()) {
            System.out.println(String.format("Number of USB devices changed from %d to %d", previousSystemState.getUsbDevices(), lastSystemState.getUsbDevices()));
        }
    }
}
