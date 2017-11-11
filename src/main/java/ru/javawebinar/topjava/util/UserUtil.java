package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserUtil {

    public static final List<User> USERS = Arrays.asList(
            new User(null,"Homer Simpson", "homer@test.com", "12345a", 1000, true, EnumSet.of(Role.ROLE_USER)),
            new User(null,"Marge Simpson", "marge@test.com", "12345a", 1000, true, EnumSet.of(Role.ROLE_USER))
    );

    public static User getByEmail(Collection<User> users, String email) {
        return users.stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .get();
    }

    public static List<User> getAllSortedByName(Collection<User> users){
        return users.stream()
                .sorted(Comparator.comparing(AbstractNamedEntity::getName)).
                collect(Collectors.toList());
    }
}
