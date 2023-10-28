package com.example.quanlycuahangtrasua.Model;

public class Products {
    private String pname, ingre, price, image, pid;

    public Products() {
    }

    public Products(String pname, String ingre, String price, String image, String pid) {
        this.pname = pname;
        this.ingre = ingre;
        this.price = price;
        this.image = image;
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getIngre() {return ingre;}

    public void setIngre(String ingre) {this.ingre = ingre;}

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}

