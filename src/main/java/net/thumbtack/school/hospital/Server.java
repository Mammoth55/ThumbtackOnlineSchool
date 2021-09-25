package net.thumbtack.school.hospital;

import net.thumbtack.school.hospital.service.DoctorService;
import net.thumbtack.school.hospital.service.PatientService;

public class Server {

    public static final Server instance = new Server();
    private static final String DATABASE_FILE = "src/main/resources/data_file.txt";

    private final DoctorService doctorService = new DoctorService();
    private final PatientService patientService = new PatientService();

    private Server() {
    }

    public void startServer(String savedDataFileName) {
        if (savedDataFileName != null) {
            // loadDatabase(savedDataFileName);
        }
    }

    public void stopServer(String savedDataFileName) {
        if (savedDataFileName != null) {
            // saveDatabase(savedDataFileName);
        }
    }

    public String registerDoctor(String requestJsonString) {
        return doctorService.register(requestJsonString);
    }

    public String getDoctorByToken(String token) {
        return doctorService.getDoctorByToken(token);
    }

    public String loginDoctor(String requestJsonString) {
        return doctorService.login(requestJsonString);
    }

    public String logoutDoctor(String requestJsonString) {
        return doctorService.logout(requestJsonString);
    }
}