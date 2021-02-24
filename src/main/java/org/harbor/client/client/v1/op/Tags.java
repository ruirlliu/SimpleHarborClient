package org.harbor.client.client.v1.op;

import cn.hutool.core.util.StrUtil;
import org.harbor.client.client.model.ListFilter;
import org.harbor.client.client.model.Tag;
import org.harbor.client.client.v1.DefaultHarborClientV1;
import org.harbor.client.client.v1.HarborResponse;

import java.util.List;
import java.util.Objects;

/**
 * @author lr
 * @date 2021/2/24
 */
public class Tags {

    private final String artifactBaseApi;
    private final String reference;
    private final DefaultHarborClientV1 client;

    public Tags(String artifactBaseApi, String reference, DefaultHarborClientV1 client) {
        this.artifactBaseApi = artifactBaseApi;
        this.reference = reference;
        this.client = client;
    }

    public List<Tag> list(ListFilter filter) {
        String tagBaseApi = getTagBaseApi();
        if (filter == null) {
            filter = ListFilter.DEFAULT;
        }
        String query = filter.getQuery();
        int page = filter.getPage();
        int pageSize = filter.getPageSize();
        tagBaseApi += "?page=" + page + "&page_size=" + pageSize;
        if (StrUtil.isNotEmpty(query)) {
            tagBaseApi += "&q=" + query;
        }
        return client.list(tagBaseApi, Tag.class);
    }

    public HarborResponse create(Tag tag) {
        Objects.requireNonNull(tag, "tag can not be null");
        return client.post(getTagBaseApi(), tag);
    }

    public HarborResponse delete(String tagName) {
        return client.delete(getTagBaseApi() + "/" + tagName);
    }


    public String getTagBaseApi() {
        return artifactBaseApi + "/" + reference + "/tags";
    }

}
