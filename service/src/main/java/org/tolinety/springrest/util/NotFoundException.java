package org.tolinety.springrest.util;

/**
 * Created by tolin on 10.06.2017.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg) {
        super(msg);
    }
}
