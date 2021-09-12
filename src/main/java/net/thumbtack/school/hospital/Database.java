package net.thumbtack.school.hospital;

import net.thumbtack.school.hospital.model.Allocation;
import net.thumbtack.school.hospital.model.User;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Getter;

@Getter
public class Database {

    public static final Database instance = new Database();

    private final Map<String, User> users = new HashMap<>(); // login -> User
    private final Set<Allocation> allocations = new HashSet<>();
    private final Map<String, User> tokens = new HashMap<>(); // token -> User

    private Database() {
    }
}