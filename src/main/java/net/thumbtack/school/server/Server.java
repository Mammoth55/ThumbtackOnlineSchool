package net.thumbtack.school.server;

public class Server {

    public static final Server instance = new Server();
    private static final String DATA_FILE = "src/main/resources/data_file.txt";

    public void startServer(String savedDataFileName) {
        if (savedDataFileName != null) {
            loadData(savedDataFileName);
        }
    }

    private boolean loadData(String savedDataFileName) {

        return true;
    }

    public void stopServer(String savedDataFileName) {
        if (savedDataFileName != null) {
            saveData(savedDataFileName);
        }
    }

    private boolean saveData(String savedDataFileName) {

        return true;
    }
}