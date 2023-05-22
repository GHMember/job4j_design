package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        boolean bool = true;
        char ch = 'x';
        long lg = 1L;
        short sh = 0;
        float fl = 1.0f;
        double db = 1.0;
        byte bt = 1;
        LOG.debug("User info name : {}, age : {}, bool : {}, ch : {}, lg : {}, sh : {}, fl : {}, db : {}, bt : {}",
                name, age, bool, ch, lg, sh, fl, db, bt);
    }
}
