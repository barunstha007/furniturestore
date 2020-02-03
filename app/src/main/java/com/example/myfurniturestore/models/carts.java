package com.example.myfurniturestore.models;

public class carts {
    String _id,product_name, product_desc, image, quantity, price, User;

    public carts(String _id, String product_name, String product_desc, String image, String quantity, String price, String user) {
        this._id = _id;
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
        User = user;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    @Override
    public String toString() {
        return "groceries{" +
                "_id='" + _id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_desc='" + product_desc + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                ", image='" + User + '\'' +
                '}';
    }
}