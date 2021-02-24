package org.harbor.client.client.v1.op;

import org.harbor.client.client.v1.HarborResponse;

/**
 * @author lr
 * @date 2021/2/24
 */
public class TagsHandler {

    private final String tagBaseApi;
    private final String tagName;
    private final Tags tags;

    public TagsHandler(String tagBaseApi, String tagName, Tags tags) {
        this.tagBaseApi = tagBaseApi;
        this.tagName = tagName;
        this.tags = tags;
    }

    public HarborResponse delete() {
        return null;
    }
}
