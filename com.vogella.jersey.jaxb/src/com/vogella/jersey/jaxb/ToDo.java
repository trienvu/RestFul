package com.vogella.jersey.jaxb;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
// JAX-RS supports an automatic mapping from JAXB annotated class to XML and
// JSON
// Isn't that cool?
// If you want you can define the order in which the fields are written
// Optional
@XmlType(propOrder = { "summary", "description" })
public class ToDo {
	private String summary;
	private String description;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
