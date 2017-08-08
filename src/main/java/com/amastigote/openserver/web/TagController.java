package com.amastigote.openserver.web;

import com.amastigote.openserver.data.model.remote.Response;
import com.amastigote.openserver.data.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/tag")
public class TagController {
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Response updateTags(
            @RequestParam(value = "serial", defaultValue = "") String tagsSerial
    ) {
        Response response = new Response();
        try {
            String localTagsSerial = tagService.getTagTableHash();
            if (!tagsSerial.equals(localTagsSerial)) {
                response
                        .setStat(Response.Status.COMPLETE)
                        .setMsg(localTagsSerial)
                        .setObj(tagService.findAllTagNames());
            } else response.setStat(Response.Status.ERROR);
        } catch (Exception ignored) {
            response.setStat(Response.Status.EXCEPTION);
        }
        return response;
    }
}
