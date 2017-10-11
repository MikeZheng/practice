package com.zrich;

public interface IPropertyDao {

	boolean createProperty(String nodeName, String value, String comment);

	boolean updateProperty(String nodeName, String value, String comment);

	void deleteProperty(String nodeName);

	public void deleteGorup(String nodeName);

	boolean createGroup(String nodeName);

}
