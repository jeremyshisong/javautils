/**
 * Project: o2osearchservice
 * 
 * File Created at 2015-4-13
 * $Id$
 * 
 */
package hello.model;

/**
 * TODO Comment of Param
 * 
 * @author shisong
 * 
 */
public class InfoObject {

	private String _id;
	private String _name;
	private String _package;
	private String _purchase;
	private String _cost;

	public InfoObject(String _id, String _name, String _package, String _purchase, String _cost) {
		this._id = _id;
		this._name = _name;
		this._package = _package;
		this._purchase = _purchase;
		this._cost = _cost;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_package() {
		return _package;
	}

	public void set_package(String _package) {
		this._package = _package;
	}

	public String get_purchase() {
		return _purchase;
	}

	public void set_purchase(String _purchase) {
		this._purchase = _purchase;
	}

	public String get_cost() {
		return _cost;
	}

	public void set_cost(String _cost) {
		this._cost = _cost;
	}
}
