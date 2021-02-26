package org.harbor.client.client.exception;

/**
 * @author lr
 * @date 2021/2/5
 */
public class HarborClientException extends RuntimeException {

    private final int statusCode;

    public HarborClientException(final int statusCode, final String s) {
        super("statusCode=" + statusCode + ", " + "message=" + s);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

}
