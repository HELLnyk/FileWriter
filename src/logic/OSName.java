package logic;

public class OSName {

    public static boolean isWindows(){
        return (getOS().contains("win"));
    }

    public static boolean isUnix(){
        return (getOS().contains("nix") || getOS().contains("nux"));
    }

    private static String getOS(){
        return System.getProperty("os.name").toLowerCase();
    }
}
