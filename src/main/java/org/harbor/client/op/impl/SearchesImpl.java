package org.harbor.client.op.impl;

import cn.hutool.core.util.StrUtil;
import org.harbor.client.model.Search;
import org.harbor.client.model.SearchType;
import org.harbor.client.op.Searches;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lr
 * @date 2021/2/24
 */
class SearchesImpl implements Searches {

    private final DefaultHarborClientV2 client;
    private String query;

    private Set<SearchType> types = new HashSet<>(8);
    SearchesImpl(DefaultHarborClientV2 client) {
        this.client = client;
    }

    @Override
    public Search doSearch() {
        String searchApi = getSearchApi();
        if (StrUtil.isNotEmpty(query)) {
            searchApi += "?q=" + query;
        }
        Search search = client.get(searchApi, Search.class);
        return search;
    }

    @Override
    public Searches query(String query) {
        return query(query, SearchType.values());
    }

    private Searches query(String name, SearchType... type) {
        this.query = name;
        types.addAll(Arrays.asList(type));
        return this;
    }

    private String getSearchApi() {
        return "/search";
    }
}
