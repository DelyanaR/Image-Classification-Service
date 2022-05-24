package com.vmware.talentboost.ics.data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class ImageTagId implements Serializable {
    @Column(name = "image_id")
    private int imageId;

    @Column(name = "tag_id")
    private int tagId;

    public ImageTagId() {
    }

    public ImageTagId(int imageId, int tagId) {
        this.imageId = imageId;
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageTagId that = (ImageTagId) o;
        return imageId == that.imageId && tagId == that.tagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, tagId);
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
}
