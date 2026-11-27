package helpers;

import models.User;

public final class GetUser {
    private GetUser() {
    }

    private static User load(String alias) {
        String prefix = "user." + alias + ".";
        return new User(
                GetConfigProperties.get(prefix + "name"),
                GetConfigProperties.get(prefix + "role"),
                GetConfigProperties.get(prefix + "email"),
                GetConfigProperties.get(prefix + "password"));
    }

    public static User admin() {
        return load("admin");
    }

    public static User customer() {
        return load("customer");
    }
}