package com.vmware.talentboost.ics.service;

import com.vmware.talentboost.ics.data.ImageTag;
import com.vmware.talentboost.ics.data.ImageTagId;
import com.vmware.talentboost.ics.repository.JpaImageTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class JpaImageTagService implements ImageTagService{

    private JpaImageTagRepository jpaImageTagRepository;

    public JpaImageTagService(JpaImageTagRepository jpaImageTagRepository) {
        this.jpaImageTagRepository = jpaImageTagRepository;
    }

    @Override
    public ImageTag get(ImageTagId id) {
        Optional<ImageTag> imageTag = jpaImageTagRepository.findById(id);
        if (imageTag.isPresent()) {
            return imageTag.get();
        }
        throw new NoSuchElementException(
                "ImageTag with ID: " + id + " was not found!");
    }

    @Override
    public ImageTag create(ImageTag imageTag) {
        return jpaImageTagRepository.saveAndFlush(imageTag);
    }

    @Override
    public ImageTag update(ImageTag imageTag) {
        ImageTagId imageTagId=new ImageTagId(imageTag.getImageId(), imageTag.getTagId());
        ImageTag dbImageTag = get(imageTagId);
        // Check if url is modified and such url already exists.
        if (imageTag.getImageId()!=dbImageTag.getImageId() &&
                imageTag.getTagId()!=dbImageTag.getTagId() &&
                jpaImageTagRepository.findById(imageTagId).isPresent()) {
            throw new IllegalArgumentException();
        }

        dbImageTag.setImageId(imageTag.getImageId());
        dbImageTag.setTagId(imageTag.getTagId());
        dbImageTag.setConfidence(imageTag.getConfidence());
        jpaImageTagRepository.saveAndFlush(dbImageTag);
        return dbImageTag;
    }

    @Override
    public void delete(ImageTagId id) {
        jpaImageTagRepository.deleteById(id);
    }

    @Override
    public Optional<ImageTag> findByImageTagId(ImageTagId imageTagId) {
        return jpaImageTagRepository.findById(imageTagId);
    }

    @Override
    public Optional<ImageTag> findByImageTagName(String name){
        return jpaImageTagRepository.findByName(name);
    }

    @Override
    public void saveAll(List<ImageTag> imageTags) {
        jpaImageTagRepository.saveAll(imageTags);
    }
}
