package ca.sfu.cmpt276.as2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "rectangle")
public class Rectangle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String name;
    private double width;
    private double height;
    private String color;
    private String material;
    private int durability;
    private int rarity;


    public Rectangle() {
    }

    public Rectangle(String name,double width, double height, String color, String material, int durability, int rarity) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.color = color;
        this.material = material;
        this.durability = durability;
        this.rarity = rarity;
    }

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getColor() {
        return color;
    }

    public String getMaterial() {
        return material;
    }

    public int getDurability() {
        return durability;
    }

    public int getRarity() {
        return rarity;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }
}
