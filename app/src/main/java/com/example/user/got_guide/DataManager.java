package com.example.user.got_guide;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "GotBase")
public class DataManager {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "dateOfBirth")
    int dateOfBirth;
    @ColumnInfo(name = "dateofDeath")
    int dateofDeath;
    @ColumnInfo(name = "spouse")
    String spouse;
    @ColumnInfo(name = "imageLink")
    String imageLink;
    @ColumnInfo(name = "male")
    boolean male;
    @ColumnInfo(name = "culture")
    String culture;
    @ColumnInfo(name = "house")
    String house;
    @ColumnInfo(name = "slug")
    String slug;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "pageRank")
    float pageRank;
    @ColumnInfo(name = "books")
    String[] books;
    @ColumnInfo(name = "titles")
    String[] titles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDateofDeath() {
        return dateofDeath;
    }

    public void setDateofDeath(int dateofDeath) {
        this.dateofDeath = dateofDeath;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPageRank() {
        return pageRank;
    }

    public void setPageRank(float pageRank) {
        this.pageRank = pageRank;
    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }
}
