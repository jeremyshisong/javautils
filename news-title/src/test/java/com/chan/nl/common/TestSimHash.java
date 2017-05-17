package com.chan.nl.common;

import org.junit.Test;

/**
 * Created by shisong on 17/5/17.
 */
public class TestSimHash {
    @Test
    public void testSimHashValue() {
        String s = "This is a test string for testing";
        SimHash hash1 = new SimHash(s, 64);
        System.out.println(hash1.getIntSimHash() + "  " + hash1.getStrSimHash());
    }


    @Test
    public void testSimHashDistance() {
        String s = "This is a test string for testing";
        SimHash hash1 = new SimHash(s, 64);
        hash1.subByDistance(hash1, 3);
    }


    @Test
    public void testSimHashDistance2() {
        String s = "This is a test string for testing";
        SimHash hash1 = new SimHash(s, 64);
        s = "This is a test string for testing, This is a test string for testing abcdef";
        SimHash hash2 = new SimHash(s, 64);
        System.out.println(hash2.getIntSimHash() + "  " + hash2.getStrSimHash());
        hash1.subByDistance(hash2, 3);
    }

    @Test
    public void testSimHashDistance3() {
        String s = "This is a test string for testing";
        SimHash hash1 = new SimHash(s, 64);
        s = "This is a test string for testing, This is a test string for testing abcdef";
        SimHash hash2 = new SimHash(s, 64);
        s = "This is a test string for testing als";
        SimHash hash3 = new SimHash(s, 64);

        int dis = hash1.getDistance(hash1.getStrSimHash(), hash2.getStrSimHash());

        System.out.println(hash1.hammingDistance(hash2) + " " + dis);

        int dis2 = hash1.getDistance(hash1.getStrSimHash(), hash3.getStrSimHash());

        System.out.println(hash1.hammingDistance(hash3) + " " + dis2);
    }
}
