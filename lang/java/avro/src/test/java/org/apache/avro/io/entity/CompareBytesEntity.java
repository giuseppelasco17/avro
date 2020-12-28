package org.apache.avro.io.entity;

public class CompareBytesEntity {
	
	private int s1;
	private int l1;
	private int s2;
	private int l2;
	private byte[] b1;
	private byte[] b2;
	
	public CompareBytesEntity(int s1, int l1, int s2, int l2, byte[] b1, byte[] b2) {
		this.s1 = s1;
		this.l1 = l1;
		this.s2 = s2;
		this.l2 = l2;
		this.b1 = b1;
		this.b2 = b2;
	}

	public int getS1() {
		return s1;
	}

	public void setS1(int s1) {
		this.s1 = s1;
	}

	public int getL1() {
		return l1;
	}

	public void setL1(int l1) {
		this.l1 = l1;
	}

	public int getS2() {
		return s2;
	}

	public void setS2(int s2) {
		this.s2 = s2;
	}

	public int getL2() {
		return l2;
	}

	public void setL2(int l2) {
		this.l2 = l2;
	}

	public byte[] getB1() {
		return b1;
	}

	public void setB1(byte[] b1) {
		this.b1 = b1;
	}

	public byte[] getB2() {
		return b2;
	}

	public void setB2(byte[] b2) {
		this.b2 = b2;
	}
	
	
	

}
