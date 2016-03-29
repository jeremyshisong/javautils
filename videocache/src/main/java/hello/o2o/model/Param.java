/**
 * Project: o2osearchservice
 * 
 * File Created at 2015-4-13
 * $Id$
 * 
 */
package hello.o2o.model;

/**
 * TODO Comment of Param
 * 
 * @author shisong
 * 
 */
public class Param {
	
	private String keyword;
	private String type;
	private String support;
	private String needVIP;
	private String queryParame;
	private String start;
	private String rows;
	private String  brief;

	/**
	 * 
	 */
	public Param() {
	}

	public Param(String keyword, String type, String support, String needVIP, String queryParame, String start, String rows, String brief) {
		this.keyword = keyword;
		this.type = type;
		this.support = support;
		this.needVIP = needVIP;
		this.queryParame = queryParame;
		this.start = start;
		this.rows = rows;
		this.brief = brief;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getNeedVIP() {
		return needVIP;
	}

	public void setNeedVIP(String needVIP) {
		this.needVIP = needVIP;
	}

	public String getQueryParame() {
		return queryParame;
	}

	public void setQueryParame(String queryParame) {
		this.queryParame = queryParame;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}
}
