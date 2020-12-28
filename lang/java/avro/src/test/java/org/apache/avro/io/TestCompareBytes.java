package org.apache.avro.io;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import org.apache.avro.io.entity.CompareBytesEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class TestCompareBytes {
	
	private CompareBytesEntity entity;
	private int expectedResult;
	
	@Parameterized.Parameters
	public static Collection<Object[]> CompareBytesParameters() throws Exception {
		String test1 = "test1";
		String test2 = "test2";
		String test3 = "test3";
		String empty = "";
		return Arrays.asList(new Object[][] {
			
			// Suite minimale
			{new CompareBytesEntity(1, 4, 1, 4, test1.getBytes(), test2.getBytes()), -1},
			{new CompareBytesEntity(0, 0, 0, 0, empty.getBytes(), empty.getBytes()), 0},
			//Coverage
			{new CompareBytesEntity(1, 4, 1, 3, test1.getBytes(), test1.getBytes()), 1},
			//Mutation
			{new CompareBytesEntity(1, 4, 1, 3, test1.getBytes(), test2.getBytes()), 1},
			{new CompareBytesEntity(1, 3, 1, 4, test1.getBytes(), test3.getBytes()), -1},
		});
	}
	

	public TestCompareBytes(CompareBytesEntity entity, int expectedResult) {
		this.entity = entity;
		this.expectedResult = expectedResult;
	}
	@Test
	public void test() {
		assertEquals(expectedResult, BinaryData.compareBytes(entity.getB1(), entity.getS1(), 
				entity.getL1(), entity.getB2(), entity.getS2(), entity.getL2()));
	}

}
