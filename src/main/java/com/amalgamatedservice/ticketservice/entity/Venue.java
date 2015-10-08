package com.amalgamatedservice.ticketservice.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raskolnikov on 10/7/2015.
 */
public class Venue {

    private final Integer id;
    private final String name;

    private final Map<Integer, Level> levels;

    public Venue(Integer id, String name) {
        this.id = id;
        this.name = name;

        this.levels = new HashMap<Integer, Level>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Level> getLevels() {
        return levels;
    }
}
