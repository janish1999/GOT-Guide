package com.example.user.got_guide;

public class data {
    int id;
    int dateOfBirth;
    int dateofDeath;
    String spouse;
    String imageLink;
    boolean male;
    String culture;
    String house;
    String slug;
    String name;
    float pageRank;
    String[] books;
    String[] titles;
    String[] locations;

    public data(int id, int dateOfBirth, int dateofDeath, String spouse, String imageLink, boolean male, String culture, String house, String slug, String name, float pageRank, String[] books, String[] titles, String[] locations) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.dateofDeath = dateofDeath;
        this.spouse = spouse;
        this.imageLink = imageLink;
        this.male = male;
        this.culture = culture;
        this.house = house;
        this.slug = slug;
        this.name = name;
        this.pageRank = pageRank;
        this.books = books;
        this.titles = titles;
        this.locations = locations;
    }

    public int getId() {
        return id;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public int getDateofDeath() {
        return dateofDeath;
    }

    public String getSpouse() {
        return spouse;
    }

    public String getImageLink() {
        return imageLink;
    }

    public boolean isMale() {
        return male;
    }

    public String getCulture() {
        return culture;
    }

    public String getHouse() {
        return house;
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public float getPageRank() {
        return pageRank;
    }

    public String[] getBooks() {
        return books;
    }

    public String[] getTitles() {
        return titles;
    }

    public String[] getLocations() {
        return locations;
    }
}