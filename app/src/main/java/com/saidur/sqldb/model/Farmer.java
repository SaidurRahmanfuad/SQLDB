package com.saidur.sqldb.model;

public class Farmer {
    private String id;
    private String name;
    private String fname;
    private String status;

    public Farmer(String id,String name, String fname, String status) {
        this.id = id;
        this.name = name;
        this.fname = fname;
        this.status = status;
    }

    public Farmer() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
