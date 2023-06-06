package com.revature.models;

public class Villain {
    /*villain_id serial PRIMARY KEY,
	villain_name TEXT,
	villain_weapon TEXT*/

    private int villain_id;
    private String villain_name;
    private String villain_weapon;

    //Constructors
    public Villain() { //No args constructor
    }

    public Villain(int villain_id, String villain_name, String villain_weapon) {
        this.villain_id = villain_id;
        this.villain_name = villain_name;
        this.villain_weapon = villain_weapon;
    }

    public Villain(String villain_name, String villain_weapon) {// All args except primary key
        this.villain_name = villain_name;
        this.villain_weapon = villain_weapon;
    }

    //Getters and Setters

    public int getVillain_id() {
        return villain_id;
    }

    public void setVillain_id(int villain_id) {
        this.villain_id = villain_id;
    }

    public String getVillain_name() {
        return villain_name;
    }

    public void setVillain_name(String villain_name) {
        this.villain_name = villain_name;
    }

    public String getVillain_weapon() {
        return villain_weapon;
    }

    public void setVillain_weapon(String villain_weapon) {
        this.villain_weapon = villain_weapon;
    }

    @Override
    public String toString() {
        return "Villain{" +
                "villain_id=" + villain_id +
                ", villain_name='" + villain_name + '\'' +
                ", villain_weapon='" + villain_weapon + '\'' +
                '}';
    }
}
