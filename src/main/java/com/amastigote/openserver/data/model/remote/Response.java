package com.amastigote.openserver.data.model.remote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.util.HashMap;
import java.util.Map;

public class Response {

    @JsonIgnore
    private static Map<Status, Integer> statusMap
            = new HashMap<Status, Integer>() {{
        put(Status.COMPLETE, 0x00); // logic complete as expected
        put(Status.ERROR, 0x01); // logic uncomplete or result not as expected
        put(Status.EXCEPTION, 0x02); // server internal error
    }};
    private int stat;
    private String msg;
    private Object obj;

    public int getStat() {
        return stat;
    }

    public Response setStat(Status stat) {
        this.stat = statusMap.get(stat);
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObj() {
        return obj;
    }

    public Response setObj(Object obj) {
        this.obj = obj;
        return this;
    }

    @JsonIgnoreType
    public enum Status {
        COMPLETE,
        ERROR,
        EXCEPTION
    }
}
