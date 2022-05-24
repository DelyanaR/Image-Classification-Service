package com.vmware.talentboost.ics.service;

import com.vmware.talentboost.ics.data.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Tag get(int id);

    Tag get(String name);

    Tag create(Tag tag);

    void delete(int id);

    Optional<Tag> findByTagId(Integer tagId);

    Optional<Tag> findByTagName(String name);

    void saveAll(List<Tag> tags);
}
