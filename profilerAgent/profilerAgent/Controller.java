package profilerAgent;

import java.io.IOException;
import java.util.Scanner;
import one.profiler.AsyncProfiler;

/**
 * Class for communication between user and profiler.
 * Uses AsyncProfiler API which serves as a wrapper around
 * async-profiler native library.
 */
public class Controller implements Runnable {

    @Override
    public void run() {
        String tmp = "";
        AsyncProfiler profiler = AsyncProfiler.getInstance();
        Scanner in = new Scanner(System.in);

        while (true) {
            if (in.hasNextLine())
                tmp = in.nextLine();

            if(tmp.equals("") || tmp.equals("help")) {
                usage();
                continue;
            }

            try {
                System.out.println(profiler.execute(tmp));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(tmp.equals("stop")){
		System.out.println("Profiling stopped");
		break;
	    }

            tmp = "";
        }
    }

    private static final void usage() {

        System.out.println("Usage: arg[,arg...]\n" + "arguments:\n" +
                " start          start profiling and return immediately\n" +
                " stop           stop profiling\n" +
                " status         print profiling status (inactive / running for X seconds)\n" +
                " collect        collect profile for the specified period of time\n" +
                "                and then stop (default action)\n" +
                " list           show the list of available profiling events\n" +
                " event=EVENT    which event to trace (cpu, alloc, lock, cache-misses etc.)\n" +
                " collapsed[=C]  dump collapsed stacks (the format used by FlameGraph script)\n" +
                "                C is counter type: 'samples' or 'total'\n" +
                " svg[=C]        produce Flame Graph in SVG format\n" +
                "                C is counter type: 'samples' or 'total'\n" +
                " summary        dump profiling summary (number of collected samples of each type)\n" +
                " traces[=N]     dump top N call traces\n" +
                " flat[=N]       dump top N methods (aka flat profile)\n" +
                " interval=N     sampling interval in ns (default: 1'000'000, i.e. 1 ms)\n" +
                " framebuf=N     size of the buffer for stack frames (default: 1'000'000)\n" +
                " threads        profile different threads separately\n" +
                " simple         simple class names instead of FQN\n" +
                " title=TITLE    FlameGraph title\n" +
                " width=PX       FlameGraph image width\n" +
                " height=PX      FlameGraph frame height\n" +
                " minwidth=PX    FlameGraph minimum frame width\n" +
                " reverse        generate stack-reversed FlameGraph\n" +
                " file=FILENAME  output file name for dumping\n");
    }
}
