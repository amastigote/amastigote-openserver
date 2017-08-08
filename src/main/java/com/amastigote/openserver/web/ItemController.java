package com.amastigote.openserver.web;

import com.amastigote.openserver.data.model.local.Item;
import com.amastigote.openserver.data.model.remote.ItemPageObj;
import com.amastigote.openserver.data.model.remote.ItemRequestBody;
import com.amastigote.openserver.data.model.remote.Response;
import com.amastigote.openserver.data.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response post(@RequestBody ItemRequestBody itemRequestBody) {
        Response response = new Response();
        try {
            if (itemService.findItemByURL(itemRequestBody.getUrl()) != null) {
                response.setStat(Response.Status.ERROR);
            } else {
                itemService.saveWithTagMetas(
                        new Item()
                                .setTitle(itemRequestBody.getTitle())
                                .setUrl(itemRequestBody.getUrl())
                        , itemRequestBody.getTags());
                response.setStat(Response.Status.COMPLETE);
            }
        } catch (Exception ignored) {
            response.setStat(Response.Status.EXCEPTION);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Response update(
            @RequestBody ItemRequestBody itemRequestBody
    ) {
        Response response = new Response();
        try {
            Item item = itemService.findItemByURL(itemRequestBody.getUrl());
            if (item == null)
                response.setStat(Response.Status.ERROR);
            else {
                itemService.saveWithTagMetas(
                        item.setTitle(itemRequestBody.getTitle()).setUrl(item.getUrl()),
                        itemRequestBody.getTags());
                response.setStat(Response.Status.COMPLETE);
            }
        } catch (Exception ignored) {
            response.setStat(Response.Status.EXCEPTION);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Response get(
            @RequestParam("url") String url
    ) {
        Response response = new Response();
        try {
            Item item = itemService.findItemByURL(url);
            if (item != null)
                response
                        .setStat(Response.Status.COMPLETE)
                        .setObj(item);
            else
                response.setStat(Response.Status.ERROR);
        } catch (Exception ignored) {
            response.setStat(Response.Status.EXCEPTION);
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Response delete(
            @RequestBody ItemRequestBody itemRequestBody
    ) {
        Response response = new Response();
        try {
            if (itemService.findItemByURL(itemRequestBody.getUrl()) != null) {
                itemService.deleteItemByURL(itemRequestBody.getUrl());
                response.setStat(Response.Status.COMPLETE);
            } else {
                response.setStat(Response.Status.ERROR);
            }
        } catch (Exception ignored) {
            response.setStat(Response.Status.EXCEPTION);
        }
        return response;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Response list(
            @RequestParam int page,
            @RequestParam(defaultValue = "", name = "tag[]") String[] tags
    ) {
        Response response = new Response();
        try {
            PageRequest pageRequest = new PageRequest(page, 20, Sort.Direction.DESC, "id");
            Page<Item> itemPage;
            if (tags.length != 0)
                itemPage = itemService.findItemsByTagNamesPageable(tags, pageRequest);
            else
                itemPage = itemService.findAllPageable(pageRequest);
            if (itemPage.hasContent()) {
                response
                        .setStat(Response.Status.COMPLETE)
                        .setObj(new ItemPageObj()
                                .setItems(itemPage.getContent())
                                .setIsFirst(itemPage.isFirst())
                                .setIsLast(itemPage.isLast())
                                .setCurrentPage(page + 1));
            } else {
                response.setStat(Response.Status.ERROR);
            }
        } catch (Exception ignored) {
            response.setStat(Response.Status.EXCEPTION);
        }
        return response;
    }
}
