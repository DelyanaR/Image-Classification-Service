package com.vmware.talentboost.ics.service;

import com.vmware.talentboost.ics.data.ImageTag;
import com.vmware.talentboost.ics.data.ImageTagId;
import com.vmware.talentboost.ics.data.Tag;

import java.util.List;
import java.util.Optional;

public interface ImageTagService {
    ImageTag get(ImageTagId id);

    Optional<ImageTag> findByImageTagName(String name);

    ImageTag create(ImageTag imageTag);

    ImageTag update(ImageTag imageTag);

    void delete(ImageTagId id);

    Optional<ImageTag> findByImageTagId(ImageTagId imageTagId);

    void saveAll(List<ImageTag> imageTags);
}
