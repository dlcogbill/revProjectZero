package com.revature.models;

public class Hero {
    /*hero_id serial PRIMARY KEY,
	hero_name TEXT,
	hero_weapon TEXT*/

    private int hero_id;
    private String hero_name;
    private String hero_weapon;

    //Constructors

    public Hero() {// No Args
    }

    public Hero(String hero_name, String hero_weapon) {// All Args except primary key
        this.hero_name = hero_name;
        this.hero_weapon = hero_weapon;
    }

    //Getters and Setters

    public int getHero_id() {
        return hero_id;
    }

    public void setHero_id(int hero_id) {
        this.hero_id = hero_id;
    }

    public String getHero_name() {
        return hero_name;
    }

    public void setHero_name(String hero_name) {
        this.hero_name = hero_name;
    }

    public String getHero_weapon() {
        return hero_weapon;
    }

    public void setHero_weapon(String hero_weapon) {
        this.hero_weapon = hero_weapon;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "hero_id=" + hero_id +
                ", hero_name='" + hero_name + '\'' +
                ", hero_weapon='" + hero_weapon + '\'' +
                '}';
    }
}
