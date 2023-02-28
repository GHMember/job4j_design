package ru.job4j.generics;

public class Role extends Base {

    private final String rolename;

    public Role(String id, String role) {
        super(id);
        this.rolename = role;
    }

    public String getRolename() {
        return rolename;
    }
}
