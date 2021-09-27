package net.thumbtack.school.hospital.exception;

public class ServerException extends Exception {

    public ServerException(ErrorCode code) {
        super(code.getDescription());
    }
}