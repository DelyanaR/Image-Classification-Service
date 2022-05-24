package com.vmware.talentboost.ics.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@IdClass(ImageTagId.class)
public class ImageTag {
    @Id
    private int imageId;

    @Id
    private int tagId;

    @ManyToOne
    @MapsId("imageId")
    @JsonIgnore
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne
    @MapsId("tagId")
    @JsonIgnore
    @JoinColumn(name = "tag_id")
    private Tag tag;

    private String name;

    private double confidence;

    public ImageTag() {
    }

    public ImageTag(int imageId, int tagId, String name, double confidence) {
        this.imageId = imageId;
        this.tagId = tagId;
        this.name=name;
        this.confidence = confidence;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "name:" + name  +
                ", confidence=" + confidence;
    }
}
