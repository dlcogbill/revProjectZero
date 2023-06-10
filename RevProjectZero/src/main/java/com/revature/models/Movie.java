package com.revature.models;

public class Movie {
    /*movie_id serial PRIMARY KEY,
	movie_name TEXT,
	movie_release_year int,
	hero_id_fk int REFERENCES heroes(hero_id),
	villain_id_fk int REFERENCES villains(villain_id)*/
    private int movie_id;
    private String movie_name;
    private int movie_release_year;
    private int hero_id_fk;
    private Hero hero;
    private int villain_id_fk;
    private Villain villain;

    //Constructors
    public Movie() { // No args constructor
    }

    public Movie(String movie_name, int movie_release_year) { // All args except primary key and foreign keys
        this.movie_name = movie_name;
        this.movie_release_year = movie_release_year;
    }

    public Movie(int movie_id, String movie_name, int movie_release_year) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_release_year = movie_release_year;
    }

    public Movie(String movie_name, int movie_release_year, int hero_id_fk, int villain_id_fk) { // All args except primary key and foreign key objects
        this.movie_name = movie_name;
        this.movie_release_year = movie_release_year;
        this.hero_id_fk = hero_id_fk;
        this.villain_id_fk = villain_id_fk;
    }

    public Movie(int movie_id, String movie_name, int movie_release_year, Hero hero, Villain villain) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_release_year = movie_release_year;
        this.hero = hero;
        this.villain = villain;
    }

    public Movie(int movie_id, String movie_name, int movie_release_year, int hero_id_fk, int villain_id_fk) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_release_year = movie_release_year;
        this.hero_id_fk = hero_id_fk;
        this.villain_id_fk = villain_id_fk;
    }

    public Movie(int movie_id, String movie_name, int movie_release_year, int hero_id_fk, Hero hero, int villain_id_fk, Villain villain) {
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_release_year = movie_release_year;
        this.hero_id_fk = hero_id_fk;
        this.hero = hero;
        this.villain_id_fk = villain_id_fk;
        this.villain = villain;
    }

    //Getters and Setters

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public int getMovie_release_year() {
        return movie_release_year;
    }

    public void setMovie_release_year(int movie_release_year) {
        this.movie_release_year = movie_release_year;
    }

    public int getHero_id_fk() {
        return hero_id_fk;
    }

    public void setHero_id_fk(int hero_id_fk) {
        this.hero_id_fk = hero_id_fk;
    }

    public int getVillain_id_fk() {
        return villain_id_fk;
    }

    public void setVillain_id_fk(int villain_id_fk) {
        this.villain_id_fk = villain_id_fk;
    }

    public Hero getHero() { return hero; }

    public void setHero(Hero hero) { this.hero = hero; }

    public Villain getVillain() { return villain; }

    public void setVillain(Villain villain) { this.villain = villain; }

    @Override
    public String toString() {
        String tab = "  ";
        return "Movie{" + "\n"  +
                tab + "movie_id=" + movie_id + "\n"  +
                tab + "movie_name='" + movie_name + "\n"  +
                tab + "movie_release_year=" + movie_release_year + "\n"  +
                tab + "hero_id_fk=" + hero_id_fk + "\n"  +
                tab + "hero=" + hero + "\n"  +
                tab + "villain_id_fk=" + villain_id_fk + "\n"  +
                tab + "villain=" + villain + "\n"  +
                '}';
    }
}
