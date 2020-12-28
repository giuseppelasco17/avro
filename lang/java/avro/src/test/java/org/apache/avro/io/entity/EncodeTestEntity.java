package org.apache.avro.io.entity;

public class EncodeTestEntity {
	
	private int n;
	private byte[] buff;
	private int pos;
	private int hashBuff;

	public EncodeTestEntity(int n, byte[] buff, int pos, int hashBuff) {
			this.n = n;
			this.buff = buff;
			this.pos = pos;
			this.hashBuff = hashBuff;
		}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public byte[] getBuff() {
		return buff;
	}

	public void setBuff(byte[] buff) {
		this.buff = buff;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getHashBuff() {
		return hashBuff;
	}

	public void setHashBuff(int hashBuff) {
		this.hashBuff = hashBuff;
	}

}
