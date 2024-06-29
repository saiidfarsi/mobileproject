// Prescription.java
package com.example.getwellapp;

public class listViewpress {
    private int id;
    private String name;
    private String startDate;
    private String endDate;

    public listViewpress(int id, String name, String startDate, String endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
