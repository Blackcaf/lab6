package utility;

public class SilentConsole implements Console {
    @Override
    public void println(String message) {
    }

    @Override
    public String readln() {
        return null; 
    }
}
