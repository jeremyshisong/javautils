/**
 * Project: o2osearchservice
 * 
 * File Created at 2015-6-17
 * $Id$
 * 
 */
package hello.demo.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO Comment of ParamsMap
 * @author shisong
 *
 */
public class ParamsMap {
	public static final Map<String, String> params = new HashMap<String, String>();
	static {
		params.put("keyword","微信");
		params.put("bizid","app");
		params.put("ruleId","167");
		params.put("queryParame","mincount=20");
		params.put("start","0");
		params.put("rows","10");
		params.put("brief","美");
	}
}
