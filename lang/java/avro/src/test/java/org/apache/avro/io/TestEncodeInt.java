package org.apache.avro.io;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;

import org.apache.avro.io.entity.EncodeTestEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestEncodeInt {
	private EncodeTestEntity entity;
	private Object expectedResult;
	
	@Parameterized.Parameters
	public static Collection<Object[]> EncodeIntParameters() throws Exception {
		return Arrays.asList(new Object[][] {
			
			// Suite minimale
			{new EncodeTestEntity(-1, new byte[5], -1, 0), ArrayIndexOutOfBoundsException.class},
			{new EncodeTestEntity(0, new byte[5], 0, 1234), 1},
			{new EncodeTestEntity(1, new byte[0], 1, 0), ArrayIndexOutOfBoundsException.class},
			// Coverage
			{new EncodeTestEntity(63, new byte[5], 1, 31442), 1},
			{new EncodeTestEntity(63*128, new byte[5], 1, 8291538), 2},
			{new EncodeTestEntity(63*128*128, new byte[5], 0, 8291410), 3},
			{new EncodeTestEntity(63*128*128*128, new byte[5], 0, 2122351698), 4},
			{new EncodeTestEntity(2*128*128*128*128, new byte[5], 0, -2139061162), 5},
			
		});
	}
	
	public TestEncodeInt(EncodeTestEntity entity, Object expectedResult) {
		this.entity = entity;
		this.expectedResult = expectedResult;
	}


	@Test
	public void test() {
		int result; 
		try {
			result = BinaryData.encodeInt(entity.getN(), entity.getBuff(), entity.getPos());
			assertEquals(expectedResult, result);
			assertEquals(entity.getHashBuff(), BitSet.valueOf(entity.getBuff()).hashCode());// hash per mutation
		} catch (ArrayIndexOutOfBoundsException exception) {
			assertEquals(expectedResult, exception.getClass());
		}
	}

}
