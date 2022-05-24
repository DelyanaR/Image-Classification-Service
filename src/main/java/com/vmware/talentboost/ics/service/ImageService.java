package com.vmware.talentboost.ics.service;

import com.vmware.talentboost.ics.data.Image;
import com.vmware.talentboost.ics.data.ImageTag;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    Image get(int id);

    Optional<Image> get(String url);

    Image create(Image image);

    Image update(Image image);

    List<Image> getAll();

    void delete(int id);

    void saveAll(List<Image> images);
}
