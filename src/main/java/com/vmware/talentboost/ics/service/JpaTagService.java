package com.vmware.talentboost.ics.service;

import com.vmware.talentboost.ics.data.Image;
import com.vmware.talentboost.ics.data.ImageTag;
import com.vmware.talentboost.ics.data.Tag;
import com.vmware.talentboost.ics.repository.JpaTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class JpaTagService implements TagService{

    private JpaTagRepository jpaTagRepository;

    public JpaTagService(JpaTagRepository jpaTagRepository) {
        this.jpaTagRepository = jpaTagRepository;
    }

    //get a tag via id
    @Override
    public Tag get(int id) {
        Optional<Tag> tag = jpaTagRepository.findById(id);
        if (tag.isPresent()) {
            return tag.get();
        }
        throw new NoSuchElementException(
                "Tag with ID: " + id + " was not found!");
    }

    //get a tag via its name
    @Override
    public Tag get(String name) {
        Optional<Tag>tag=jpaTagRepository.findByName(name);
        //System.out.println(tag.get());
        return tag.get();
    }

    //create a new tag
    @Override
    public Tag create(Tag tag) {
        return jpaTagRepository.saveAndFlush(tag);
    }

    //delete a tag by id
    @Override
    public void delete(int id) {
        jpaTagRepository.deleteById(id);
    }

    //find tag by id
    @Override
    public Optional<Tag> findByTagId(Integer tagId) {
        return jpaTagRepository.findById(tagId);
    }

    @Override
    public Optional<Tag> findByTagName(String name) {
        return jpaTagRepository.findByName(name);
    }

    //save all tags
    @Override
    public void saveAll(List<Tag> tags) {
        jpaTagRepository.saveAll(tags);
    }
}
