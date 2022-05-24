package com.vmware.talentboost.ics.data;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name",unique = true)
    private String name;

    @OneToMany(mappedBy = "tag")
    private List<ImageTag> imageTags;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public int getTagId() {
        return id;
    }

    public void setTagId(int tagId) {
        this.id = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ImageTag> getImageTags() {
        return imageTags;
    }

    public void setImageTags(List<ImageTag> imageTags) {
        this.imageTags = imageTags;
    }
}
