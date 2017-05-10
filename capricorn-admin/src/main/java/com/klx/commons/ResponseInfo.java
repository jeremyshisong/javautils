/**
 * Project: galaxy2-console-web
 * <p/>
 * File Created at 2015-7-14
 * $Id$
 */
package com.klx.commons;


/**
 * TODO Comment of ResponseModel
 *
 * @author shisong
 *
 */

public class ResponseInfo {

    private String code;
    private String message;
    private Object value;

    public ResponseInfo() {

    }


    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
