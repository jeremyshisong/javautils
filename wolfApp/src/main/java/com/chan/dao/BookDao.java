/**
 * Project: o2osearchservice
 * 
 * File Created at 2015-4-11
 * $Id$
 * 
 */
package com.chan.dao;

import org.springframework.stereotype.Component;

/**
 * TODO Comment of BookDao
 * 
 * @author shisong
 * 
 */
@Component
public class BookDao {
 
	// 模拟数据库操作
	public void add(Object book) {
		System.out.print("Add");
	}

	public void update(Object book) {
		System.out.print("Update");

	}
}
