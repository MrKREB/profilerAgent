import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ProfilerAgent {

    //private static final int MAX_PATH = 1024;

    public static void premain(String args) {
        String procName = ManagementFactory.getRuntimeMXBean().getName();
        int pid = Integer.parseInt(procName.substring(0, procName.indexOf("@")));
        int optind = 1;
        String scriptDir = "";
        String jattach = scriptDir + "/build/jattach";
        String profiler = scriptDir + "/build/libasyncProfiler.so";
        String action = "collect";
        String mode = "cpu";
        int duration = 60;
        String file = "/tmp/async-profiler.log";
        int interval = 0;
        int framebuf = 0;
        String output = "summary,traces=200,flat=200";
        String[] buf = null;

        JavaAPI ja = new JavaAPI();

        Scanner in = new Scanner(System.in);
        while (true) {
            if (in.hasNextLine()) buf = in.nextLine().split(" ");
            if(buf == null) continue;

            for (int i = 1; i < buf.length; i += 2) {
                switch (buf[i]) {
                    case "-h":
                        usage();
                        break;
                    case "-m":
                        mode = buf[i+1];
                        break;
                    case "-d":
                        duration = Integer.parseInt(buf[i+1]);
                        break;
                    case "-f":
                        file = buf[i+1];
                        break;
                    case "-i":
                        interval = Integer.parseInt(buf[i+1]);
                        break;
                    case "-b":
                        framebuf = Integer.parseInt(buf[i+1]);
                        break;
                    case "-o":
                        output = buf[i+1];
                        break;
                    default:
                        System.out.println("Unrecognized option:" + buf[i] + ' ' + buf[i+1]);
                        usage();
                        return;
                }
            }

            action = buf[0];
            switch (action) {
                case "start":
                    ja.start(mode, file, interval, framebuf);
                    break;
                case "stop":
                    ja.stop(file, output);
                    break;
                case "status":
                    ja.status(file);
                    break;
                case "collect":
                    ja.start(mode, file, interval, framebuf);
                    show_agent_output(file);
                    ja.stop(file, output);
                    break;
                default:
            }

            show_agent_output(file);
            buf = null;
        }
    }

    private static final void usage() {
        System.out.println("Usage: $0 [action] [options]\n" + "Actions:\n" +
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
                "Example: $0 -d 30 -f profile.fg -o collapsed\n" +
                "         $0 start -i 999000\n" +
                "         $0 stop -o summary,flat\n");
    }

    private static void show_agent_output(String file) {
        try{
            Files.lines(Paths.get(file), StandardCharsets.UTF_8).forEach(System.out::println);
        }catch (IOException e){}
    }

    //static String get_temp_directory() {
    //    return "/tmp";
    //}

}
