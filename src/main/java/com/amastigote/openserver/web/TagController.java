package com.amastigote.openserver.web;

import com.amastigote.openserver.data.model.remote.Response;
import com.amastigote.openserver.data.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public Response getTags() {
        Response response = new Response();
        try {
            response
                    .setStat(Response.Status.COMPLETE)
                    .setObj(tagService.findAllTagNames());
        } catch (Exception ignored) {
            response.setStat(Response.Status.EXCEPTION);
        }
        return response;
    }
}
