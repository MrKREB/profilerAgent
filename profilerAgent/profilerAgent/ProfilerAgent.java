package profilerAgent;

import java.io.IOException;

/**
 * Java API for profiling. Runs daemon thread which
 * is needed for communication between user and profiler.
 */
public class ProfilerAgent {

    public static void premain(String args) throws IOException {
        Thread control = new Thread(new Controller(), "control");
        control.setDaemon(true);
        control.start();
    }

}
