package org.apache.avro.generic.entity;

public class TestEntity {
	private int field1;
	private int field2;
	
	public TestEntity(int field1, int field2) {
		this.setField1(field1);
		this.setField2(field2);
	}

	public int getField1() {
		return field1;
	}

	public void setField1(int field1) {
		this.field1 = field1;
	}

	public int getField2() {
		return field2;
	}

	public void setField2(int field2) {
		this.field2 = field2;
	}
	
	
}
