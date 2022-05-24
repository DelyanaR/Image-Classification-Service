package com.vmware.talentboost.ics.repository;

import com.vmware.talentboost.ics.data.Image;
import com.vmware.talentboost.ics.data.ImageTag;
import com.vmware.talentboost.ics.data.ImageTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaImageTagRepository extends JpaRepository<ImageTag, ImageTagId> {
    Optional<ImageTag>findById(final ImageTagId imageTagId);
    List<ImageTag> findByImageId(Integer imageId);
    List<ImageTag> findByTagId(Integer tagId);
    Optional<ImageTag>findByName(final String name);
}
