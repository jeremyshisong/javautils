/**
 * Project: o2osearchservice
 * 
 * File Created at 2015-6-17
 * $Id$
 * 
 */
package hello.o2o.constants;

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
		params.put("keyword","快乐");
		params.put("type","0");
		params.put("support","15");
		params.put("needVIP","false");
		params.put("queryParame","mincount=20");
		params.put("start","0");
		params.put("rows","10");
		params.put("brief","美");
	}
}
