package com.core.nlp.index;

/**
 * Created by shisong on 17/4/24.
 */
public class Term {
    private String word;
    private Term.Type type;


    /**
     * Specifies whether and how a field should be stored.
     */
    public static enum Type {

        /**
         * Store the original field value in the index. This is useful for short texts
         * like a document's title which should be displayed with the results. The
         * value is stored in its original form, i.e. no analyzer is used before it is
         * stored.
         */
        CATEGORY,

        /**
         * Do not store the field's value in the index.
         */
        DESCRIBE
    }
}
