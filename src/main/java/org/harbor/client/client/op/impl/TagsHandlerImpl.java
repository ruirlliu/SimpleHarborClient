package org.harbor.client.client.op.impl;

import org.harbor.client.client.HarborResponse;
import org.harbor.client.client.op.Tags;
import org.harbor.client.client.op.TagsHandler;

/**
 * @author lr
 * @date 2021/2/24
 */
class TagsHandlerImpl implements TagsHandler {

    private final String tagBaseApi;
    private final String tagName;
    private final Tags tags;

    TagsHandlerImpl(String tagBaseApi, String tagName, Tags tags) {
        this.tagBaseApi = tagBaseApi;
        this.tagName = tagName;
        this.tags = tags;
    }

    @Override
    public HarborResponse delete() {
        return null;
    }
}
