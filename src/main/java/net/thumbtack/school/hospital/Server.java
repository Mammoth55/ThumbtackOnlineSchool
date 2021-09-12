package net.thumbtack.school.hospital;

import net.thumbtack.school.hospital.service.DoctorService;

public class Server {

    public static final Server instance = new Server();
    private static final String DATA_FILE = "src/main/resources/data_file.txt";

    private final DoctorService doctorService = new DoctorService();

    private Server() {
    }

    public void startServer(String savedDataFileName) {
        if (savedDataFileName != null) {
            loadData(savedDataFileName);
        }
    }

    private void loadData(String savedDataFileName) {
    }

    public void stopServer(String savedDataFileName) {
        if (savedDataFileName != null) {
            saveData(savedDataFileName);
        }
    }

    private void saveData(String savedDataFileName) {
    }

    public String registerDoctor(String requestJsonString) {
        return doctorService.register(requestJsonString);
    }

    public String getDoctorByToken(String token) {
        return doctorService.getDoctorByToken(token);
    }
}