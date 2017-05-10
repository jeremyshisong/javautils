/**
 * Project: o2osearchservice
 * 
 * File Created at 2015-6-17
 * $Id$
 * 
 */
package hello.demo.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO Comment of ParamsMap
 * @author shisong
 *
 */
public class InfoFields {
	public static final List<String> fields=new ArrayList<String>();
	static {
		fields.add("id");
		fields.add("name");
		fields.add("packageName");
		fields.add("purchase");
		fields.add("cost");
	}
}
