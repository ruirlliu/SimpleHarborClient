package org.harbor.client.flag;

/**
 * @author lr
 * @date 2021/2/22
 */
public enum ResponseConfigure {

    /**
     * 请求失败，是否抛出异常
     */
    FAILED_THROW(true),

    EMPTY_IGNORE(true);

    private final boolean defaultState;
    private final int mask;

    public boolean isDefaultState() {
        return defaultState;
    }

    public int getMask() {
        return mask;
    }


    ResponseConfigure(boolean defaultState) {
        this.defaultState = defaultState;
        this.mask = (1 << ordinal());
    }

    public boolean enabled(int flag) {
        return (flag & mask) != 0;
    }

    public final static int DEFAULT_CONFIGURE = defaultConfigure();

    static int defaultConfigure() {
        int flag = 0;
        for (ResponseConfigure value : values()) {
            if (value.defaultState) {
                flag |= value.mask;
            }
        }
        return flag;
    }

}
