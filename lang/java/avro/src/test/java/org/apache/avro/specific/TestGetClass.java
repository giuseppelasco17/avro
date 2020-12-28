package org.apache.avro.specific;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericData.StringType;
import org.apache.avro.specific.OuterClassForTest.InnerClassForTest;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.Mockito;

@RunWith(Parameterized.class)
public class TestGetClass {
	
	private Schema schema;
	private Object expectedResult;
	Collection<String> array = new LinkedList<String>();
	Map<String, String> map = new HashMap<>();
	Map<Integer, Integer> mapInt = new HashMap<>();
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Parameterized.Parameters
	public static Collection<Object[]> GetClassParameters() throws Exception {
		return Arrays.asList(new Object[][] {

			// Suite minimale
			{ null, NullPointerException.class, false, false },
			{ Schema.Type.INT, Integer.TYPE, false, false }, 
			{ Schema.Type.BOOLEAN, Boolean.TYPE, false, false },
			{ Schema.Type.NULL, Void.TYPE, false, false }, 
			{ Schema.Type.LONG, Long.TYPE, false, false }, 
			{ Schema.Type.FLOAT, Float.TYPE, false, false },
			{ Schema.Type.DOUBLE, Double.TYPE, false, false }, 
			{ Schema.Type.BYTES, ByteBuffer.class, false, false },
			{ Schema.Type.STRING, CharSequence.class, false, false },
			{ Schema.Type.MAP, Map.class, false, false },
			{ Schema.Type.ARRAY, List.class, false, false },
			{ Schema.Type.RECORD, SpecificData.class, false, false },
			{ Schema.Type.FIXED, SpecificData.class, false, false },
			{ Schema.Type.ENUM, SpecificData.class, false, false },
			{ Schema.Type.UNION, Object.class, false, false },
			//Coverage
			{ Schema.Type.STRING, String.class, true, false },
			{ Schema.Type.UNION, Integer.class, true, false },
			{ Schema.Type.UNION, Object.class, false, true },
			{ Schema.Type.UNION, Integer.class, true, true },
			{ Schema.Type.ENUM, null, true, false },
			{ Schema.Type.ENUM, InnerClassForTest.class, false, true },//nested class
			{ Schema.Type.ENUM, null, true, true}//name=null, Mockito spy
		});
	}

	
	public TestGetClass(Schema.Type type, Object expectedResult, boolean flag, boolean flag2) {
		Schema schema = null;
		if(type == Type.RECORD) {
			List<Schema.Field> l = new ArrayList<Schema.Field>();
			l.add(new Schema.Field("int", Schema.create(Type.INT)));
			schema = Schema.createRecord("SpecificData", "Doc", "org.apache.avro.specific", false, l);
		}else if(type == Type.MAP) {
			schema = Schema.createMap(Schema.create(Type.INT));
		}else if(type == Type.ARRAY) {
			schema = Schema.createArray(Schema.create(Type.INT));
		}else if(type == Type.FIXED){
			schema = Schema.createFixed("SpecificData", "", "org.apache.avro.specific", 10);
		}else if(type == Type.ENUM){
			List<String> val = new ArrayList<String>();
			if(!flag & !flag2) {
				val.add("test"); 
				schema = Schema.createEnum("SpecificData", "", "org.apache.avro.specific", val);
			}else if (flag & !flag2) {
				val.add("test"); 
				schema = Schema.createEnum("SpecificData", "", "", val);
			}else if (!flag & flag2) {
				val.add("test"); 
				schema = Schema.createEnum("InnerClassForTest", "", "org.apache.avro.specific.OuterClassForTest", val);
			}else {
				val.add("test"); 
				schema = Schema.createEnum("SpecificData", "", "", val);
				schema = Mockito.spy(schema);
				Mockito.when(schema.getFullName()).thenReturn(null);
			} 
		}else if(type == Type.STRING & flag){
			Schema sc = Schema.create(type);
			GenericData.setStringType(sc, StringType.String);
			schema = sc; 
		}else if(type == Type.UNION){ 
			List<Schema> val = new ArrayList<Schema>();
			if(!flag & !flag2) {
				val.add(Schema.create(Type.INT)); 
				schema = Schema.createUnion(val);
			}else if (flag & !flag2) {
				val.add(Schema.create(Type.NULL));
				val.add(Schema.create(Type.INT)); 
				schema = Schema.createUnion(val);
			}else if (!flag & flag2) { 
				val.add(Schema.create(Type.INT)); 
				val.add(Schema.create(Type.STRING));
				schema = Schema.createUnion(val);
			}else {
				val.add(Schema.create(Type.INT)); 
				val.add(Schema.create(Type.NULL));
				schema = Schema.createUnion(val);
			}
		}else if (type == null) {
			schema = null;
		}else {
			schema = Schema.create(type);
		}
		this.schema = schema;
		this.expectedResult = expectedResult;
	}
	

	@Test
	public void testGetSchema() throws IOException, NoSuchFieldException, SecurityException {

		try {
			Assert.assertEquals(expectedResult, SpecificData.get().getClass(schema));
		} catch (Exception e) {
			Assert.assertEquals(expectedResult, e.getClass());
		}

	}

}
