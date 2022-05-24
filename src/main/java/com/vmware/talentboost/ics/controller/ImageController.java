package com.vmware.talentboost.ics.controller;

import com.vmware.talentboost.ics.data.Image;
import com.vmware.talentboost.ics.data.ImageTag;
import com.vmware.talentboost.ics.data.Tag;
import com.vmware.talentboost.ics.dto.ImageDto;
import com.vmware.talentboost.ics.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "")
@CrossOrigin
public class ImageController {

    private final ImaggaController imagga;
    private final ImageService imageService;
    private final TagService tagService;
    private final ImageTagService imageTagService;

    public ImageController(final ImageService imageService, final TagService tagService, final ImageTagService imageTagService) {
        this.imagga=new ImaggaController();
        this.imageService = imageService;
        this.tagService = tagService;
        this.imageTagService = imageTagService;
    }

    //get all images
    @GetMapping("/images")
    public List<Image> get() throws Exception {
        return imageService.getAll();
    }

    //get image by id
    @GetMapping("/images/{id}")
    public Image get(@PathVariable final Integer id) {
        try {
            return imageService.get(id);
        } catch (final NoSuchElementException e) {
            throw new IllegalArgumentException(
                    String.format("Image with ID %d not found.", id));
        }
    }

    //get image by url
    @GetMapping("/image/{url}")
    public Image get(@PathVariable final String url){
        return imageService.get(url).get();
    }

    //create an Image
    @PostMapping("/images")
    public ResponseEntity<Void> create(@RequestBody final ImageDto image) throws IOException {
        if (imageService.get(image.url).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("An image with url '%s' already exists.",
                            image.url));
        }

        String jsonResponse=imagga.getTagsForImage(image.url);

        Image createdImage=imageService.create(new Image(image.url,
                java.time.LocalDate.now().toString(), "Imagga"));
        String cut=jsonResponse.replace("{\"result\":{\"tags\":[","")
                .replace("]},\"status\":{\"text\":\"\",\"type\":\"success\"}}","")
                .replaceAll(Pattern.quote("{\"confidence\":"),"");
        String cut2=cut.replaceAll(Pattern.quote("\"tag\":{\"en\":"),"");
        String cut3=cut2.replaceAll(Pattern.quote("\"}"),"");

        String[] tags=cut3.split("},");
        //create and/or add the tags for this image
        for(String tag:tags){
            String[] split=tag.split(",\"");
            if(Double.parseDouble(split[0])<40.00){
                break;
            }
            else if (tagService.findByTagName(split[1]).isPresent()) {
                //do not create a new tag, but create an imageTag, and you will get the id of the
                //already existing tag by the name from the database
                Optional<Tag> existingTag=tagService.findByTagName(split[1]);
                Tag oldTag=existingTag.get();
                this.imageTagService.create(new ImageTag(createdImage.getId(), oldTag.getTagId(),oldTag.getName(),
                        (int)Double.parseDouble(split[0])));
            }
            else{
                Tag createdTag=tagService.create(new Tag(split[1]));
            //add a line to the imagetag table, but we need the tag id, so first we need to add the tag
                this.imageTagService.create(new ImageTag(createdImage.getId(), createdTag.getTagId(),createdTag.getName(),
                        (int)Double.parseDouble(split[0])));
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
