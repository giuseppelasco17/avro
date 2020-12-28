package org.apache.avro.generic.entity;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;

public class DeepCopyRawTestEntity<T> {
	private Type type;
	private T value;
	private Schema schema;
	
	public DeepCopyRawTestEntity(Type type, T value) {
		this.type = type;
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}



}
