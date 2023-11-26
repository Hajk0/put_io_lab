package put.io.patterns.implement;

public class MonitorRunner {

    public static void main(String args[]){
        // tworzymy monitor
        SystemMonitor monitor = new SystemMonitor();

        // tworzymy obserwatora i dodajemy do monitora
        SystemStateObserver infObserver =  new SystemInfoObserver();
        monitor.addSystemStateObserver(infObserver);

        //
        SystemStateObserver garbCollObserver = new SystemGarbageCollectorObserver();
        monitor.addSystemStateObserver(garbCollObserver);

        //
        SystemStateObserver coolerObserver = new SystemCoolerObserver();
        monitor.addSystemStateObserver(coolerObserver);

        //
        SystemStateObserver usbDeviceObserver = new USBDeviceObserver();
        monitor.addSystemStateObserver(usbDeviceObserver);

        while (true) {
            monitor.probe();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
