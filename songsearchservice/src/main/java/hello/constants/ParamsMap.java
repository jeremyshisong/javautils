/**
 * Project: o2osearchservice
 * 
 * File Created at 2015-6-17
 * $Id$
 * 
 */
package hello.constants;

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
		params.put("keyword","失落沙洲");
		params.put("page","0");
		params.put("limit","10");
		params.put("bref","a");
		params.put("geoloc","39.9,116.5");

	}
}
