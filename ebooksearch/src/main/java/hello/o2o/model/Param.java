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
	private String bizid;
	private String ruleId;
	private String queryParame;
	private String start;
	private String rows;
	private String  brief;

	/**
	 * 
	 */
	public Param() {
	}

	public Param(String keyword, String bizid, String ruleId, String queryParame, String start, String rows, String brief) {
		this.keyword = keyword;
		this.bizid = bizid;
		this.ruleId = ruleId;
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

	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
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
