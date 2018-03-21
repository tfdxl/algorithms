package com.tfdxl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianfeng on 2017/11/15.
 */
public class StrictMap<V> extends HashMap<String, V> {

    private String name;

    public StrictMap(String name, int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
        this.name = name;
    }

    public StrictMap(String name, int initialCapacity) {
        super(initialCapacity);
        this.name = name;
    }

    public StrictMap(String name) {
        super();
        this.name = name;
    }

    public StrictMap(String name, Map<String, ? extends V> m) {
        super(m);
        this.name = name;
    }

    protected static class Ambiguity {

        private String subject;

        public Ambiguity(String subject) {this.subject = subject;}

        public String getSubject() {
            return subject;
        }
    }
}
