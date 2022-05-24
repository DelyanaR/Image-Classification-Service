package com.vmware.talentboost.ics.service;

import com.vmware.talentboost.ics.data.Image;
import com.vmware.talentboost.ics.repository.JpaImageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class JpaImageService implements ImageService{

    private JpaImageRepository repository;

    public JpaImageService(JpaImageRepository repository) {
        this.repository = repository;
    }

    //get an image by id
    @Override
    public Image get(int id) {
        Optional<Image> image = repository.findById(id);
        if (image.isPresent()) {
            return image.get();
        }
        throw new NoSuchElementException(
                "Image with ID: " + id + " was not found!");
    }

    //get an image by url
    @Override
    public Optional<Image> get(String url) {
        return repository.findByUrl(url);
    }

    //create a new image
    @Override
    public Image create(Image image) {
        return repository.saveAndFlush(image);
    }

    //get all images(Gallery functionality)
    @Override
    public List<Image> getAll() {
        return repository.findAll();
    }

    //update a image
    @Override
    public Image update(Image image) {
        Image dbImage = get(image.getId());
        // Check if url is modified and such url already exists.
        if (!image.getUrl().equals(dbImage.getUrl()) &&
                repository.findByUrl(image.getUrl()).isPresent()) {
            throw new IllegalArgumentException(String.format(
                    "An image with the specified url '%s' already exists.",
                    image.getUrl()));
        }

        dbImage.setUrl(image.getUrl());
        dbImage.setTimeAdded(image.getTimeAdded());
        dbImage.setService(image.getService());
        dbImage.setHeight(image.getHeight());
        dbImage.setWidth(image.getWidth());
        dbImage.setImageTags(image.getImageTags());
        repository.saveAndFlush(dbImage);
        return dbImage;
    }

    //delete an image by id
    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }


    //save all images
    @Override
    public void saveAll(List<Image> images) {
        repository.saveAll(images);
    }
}
