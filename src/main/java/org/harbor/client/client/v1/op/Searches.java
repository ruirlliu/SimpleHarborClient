package org.harbor.client.client.v1.op;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.harbor.client.client.model.Search;
import org.harbor.client.client.model.SearchType;
import org.harbor.client.client.v1.DefaultHarborClientV1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lr
 * @date 2021/2/24
 */
public class Searches {

    private final DefaultHarborClientV1 client;
    private final String baseApi;

    private String query;

    private Set<SearchType> types = new HashSet<>(8);
    public Searches(DefaultHarborClientV1 client, String baseApi) {
        this.client = client;
        this.baseApi = baseApi;
    }

    public Search doSearch() {
        String searchApi = getSearchApi();
        if (StrUtil.isNotEmpty(query)) {
            searchApi += "?q=" + query;
        }
        Search search = client.get(searchApi, Search.class);
        return search;
    }

    public Searches query(String query) {
        return query(query, SearchType.values());
    }

    private Searches query(String name, SearchType... type) {
        this.query = name;
        types.addAll(Arrays.asList(type));
        return this;
    }

    public String getSearchApi() {
        return baseApi + "/search";
    }
}
