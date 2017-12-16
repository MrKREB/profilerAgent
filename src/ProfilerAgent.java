import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ProfilerAgent {

    //private static final int MAX_PATH = 1024;

    public static void premain(String args) {
        usage();
        String procName = ManagementFactory.getRuntimeMXBean().getName();
        int pid = Integer.parseInt(procName.substring(0, procName.indexOf("@")));
        int optind = 1;
        String scriptDir = "";
        String jattach = scriptDir + "/build/jattach";
        String profiler = scriptDir + "/build/libasyncProfiler.so";
        String action = "collect";
        String mode = "cpu";
        int duration = 60;
        String file = "";
        boolean useTMP = true;
        int interval;
        int framebuf;
        String output = "summary,traces=200,flat=200";

        Scanner in = new Scanner(System.in);
        if(in.hasNextLine())
            in.nextLine().split(" ");

        show_agent_output(useTMP, file);
    }

    private static final void usage() {
        System.out.println("Usage: $0 [action] [options] <pid>\n" + "Actions:\n" +
                "  start             start profiling and return immediately" +
                "  start             start profiling and return immediately" +
                "  stop              stop profiling" +
                "  status            print profiling status" +
                "  collect           collect profile for the specified period of time" +
                "                    and then stop (default action)" +
                "Options:" +
                "  -m mode           profiling mode: cpu|heap" +
                "  -d duration       run profiling for <duration> seconds" +
                "  -f filename       dump output to <filename>" +
                "  -i interval       sampling interval in nanoseconds" +
                "  -b bufsize        frame buffer size" +
                "  -o fmt[,fmt...]   output format: summary|traces|flat|collapsed" +
                "" +
                "Example: $0 -d 30 -f profile.fg -o collapsed 3456" +
                "         $0 start -i 999000 3456" +
                "         $0 stop -o summary,flat 3456");
    }

    private static void show_agent_output(boolean useTMP, String file) {
        if(useTMP){
            try{
                Files.lines(Paths.get(file), StandardCharsets.UTF_8).forEach(System.out::println);
            }catch (IOException e){}
        }
    }

    //static String get_temp_directory() {
    //    return "/tmp";
    //}

}
