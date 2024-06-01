package ca.sfu.cmpt276.as2.models;

import jakarta.persistence.*;

@Entity
@Table(name = "rectangle")
public class Rectangle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private double width;
    private double height;
    private String color;
    private String material;

    public Rectangle() {
    }

    public Rectangle(double width, double height, String color, String material) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.material = material;
    }

    public int getUid() {
        return uid;
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

    public void setUid(int uid) {
        this.uid = uid;
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
}
