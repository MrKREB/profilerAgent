/**
 * Created by Павел on 06.02.2018.
 */

public class JavaAPI {
    static {
        System.loadLibrary("javaapilib");
    }

    native public static void start(String mode, String file, int interval, int frame_buffer_size);
    native public static void stop(String file, String output);
    native public static void status(String file);
}
