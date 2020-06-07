package com.health.common.core.domain;

import java.util.HashMap;
import java.util.Map;
public class JsonResult extends HashMap<String, Object> {
    /**
     *
     */
    private static final long serialVersionUID = -8157613083634272196L;

    public JsonResult() {
        put("code", 0);
        put("msg", "success");
    }

    public static JsonResult error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static JsonResult error(String msg) {
        return error(500, msg);
    }

    public static JsonResult error(int code, String msg) {
        JsonResult r = new JsonResult();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static JsonResult ok(String msg) {
        JsonResult r = new JsonResult();
        r.put("msg", msg);
        return r;
    }

    public static JsonResult data(Object obj) {
        JsonResult r = new JsonResult();
        r.put("data", obj);
        return r;
    }

    public static JsonResult ok(Map<String, Object> map) {
        JsonResult r = new JsonResult();
        r.putAll(map);
        return r;
    }

    public static JsonResult ok() {
        return new JsonResult();
    }

    @Override
    public JsonResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
