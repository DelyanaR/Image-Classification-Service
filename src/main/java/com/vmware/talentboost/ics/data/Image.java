package com.vmware.talentboost.ics.data;


import javax.persistence.*;
import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "url", unique = true)
    private String url;

    @Column(name="time_added")
    private String timeAdded;

    @Column(name="service")
    private String service;

    @Column(name = "height")
    private int height;

    @Column(name = "width")
    private int width;

    @OneToMany(mappedBy = "image")
    private List<ImageTag> imageTags;

    public Image() {
    }

    public Image(String url, String timeAdded, String service) {
        this.url = url;
        this.timeAdded = timeAdded;
        this.service = service;
        setHeightAndWidth();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTimeAdded() {
        return timeAdded;
    }

    public void setTimeAdded(String timeAdded) {
        this.timeAdded = timeAdded;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    private void setHeightAndWidth(){
        URL url= null;
        try {
            url = new URL(this.url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        java.awt.Image image=new ImageIcon(url).getImage();
        setHeight(image.getHeight(null));
        setWidth(image.getWidth(null));
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<ImageTag> getImageTags() {
        return imageTags;
    }

    public void setImageTags(List<ImageTag> imageTags) {
        this.imageTags = imageTags;
    }

}
