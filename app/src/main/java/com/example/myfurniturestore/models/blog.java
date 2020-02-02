package com.example.myfurniturestore.models;

public class blog {
    private String _id;
    private String topic;
    private String description;
    private String First_name;

    public blog( String topic, String description, String first_name) {
        this._id = _id;
        this.topic = topic;
        this.description = description;
        First_name = first_name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    @Override
    public String toString() {
        return "blog{" +
                "firstname='" + First_name + '\'' +
                ", topic='" + topic + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
