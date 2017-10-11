package com.zrich;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class PropertyItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String value;
	private String comment;

	public PropertyItem(String name, String value, String comment) {
		super();
		this.name = name;
		this.value = value;
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
