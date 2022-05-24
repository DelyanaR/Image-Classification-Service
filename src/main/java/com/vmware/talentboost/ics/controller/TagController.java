package com.vmware.talentboost.ics.controller;

import com.vmware.talentboost.ics.data.Image;
import com.vmware.talentboost.ics.data.ImageTag;
import com.vmware.talentboost.ics.data.Tag;
import com.vmware.talentboost.ics.dto.ImageDto;
import com.vmware.talentboost.ics.dto.TagDto;
import com.vmware.talentboost.ics.service.ImageService;
import com.vmware.talentboost.ics.service.ImageTagService;
import com.vmware.talentboost.ics.service.JpaTagService;
import com.vmware.talentboost.ics.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin
@RequestMapping(value = "")
public class TagController {

    private final TagService tagService;

    public TagController(final TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("tag/{name}")
    public List<Image> getImagesByTagName(@PathVariable final String name) throws Exception {
        try {
            List<ImageTag> imageTags=tagService.get(name).getImageTags();
            List<Image> imageList=new ArrayList<>();
            for(ImageTag imageTag: imageTags){
                imageList.add(imageTag.getImage());
            }
            return imageList;
        } catch (final NoSuchElementException e) {
            throw new IllegalArgumentException(
                    String.format("Tag with name %d not found.", name));
        }

    }

}
