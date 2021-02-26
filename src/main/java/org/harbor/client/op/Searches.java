package org.harbor.client.op;

import org.harbor.client.model.Search;

/**
 * @author lr
 * @date 2021/2/26
 */
public interface Searches {
    Search doSearch();

    Searches query(String query);
}
