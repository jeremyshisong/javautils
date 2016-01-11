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
		params.put("keyword","都市花园");
		params.put("city","苏州");
		params.put("longitude","109.03989");
		params.put("latitude","34.251904");
		params.put("areaFilter","苏州工业园区");
		params.put("page","10");
		params.put("bref","天");
	}
}
