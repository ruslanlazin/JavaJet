package ua.pp.lazin.javajet.util.test;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Sending POST to GCM");

        GoogleApi googleApi = new GoogleApi();
        googleApi.testTimezone();
        System.out.println("oooo");
        Thread.sleep(10000);
        System.out.println("pp");
    }
}