package com.knox.advancealgo.optm.operations.string;

import org.junit.Test;

import se.l4.exobytes.Serializer;

import com.knox.advancealgo.optm.OpLogHelper;
import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.SerializerTestHelper;

/**
 * Serialization 序列化？跟传统的序列化有什么不同的呢？
 */
public class StringSerializationTest
{
	@Test
	public void test1()
	{
		test(StringDelta.builder()
			.retain(2)
			.insert("Hello World")
			.delete("Cookie")
			.done()
		);
	}

	@Test
	public void test2()
	{
		test(StringDelta.builder()
			.retain(2)
			.updateAnnotations()
				.set("key", null, true)
				.done()
			.insert("Hello World")
			.delete("Cookie")
			.updateAnnotations()
				.remove("key", true)
				.done()
			.done()
		);
	}

	@Test
	public void test3()
	{
		test("[[\"retain\",2],[\"annotations\",{\"key\":{\"oldValue\":null,\"newValue\":true}}],[\"insert\",\"Hello World\"],[\"delete\",\"Cookie\"]]", StringDelta.builder()
			.retain(2)
			.updateAnnotations()
				.set("key", null, true)
				.done()
			.insert("Hello World")
			.delete("Cookie")
			.done()
		);
	}

	private void test(Operation<StringHandler> op)
	{
		StringType type = new StringType();
		Serializer<Operation<StringHandler>> serializer = type.getSerializer();
		OpLogHelper.e("test", op);
		SerializerTestHelper.testSymmetry(serializer, op);
	}

	private void test(String json, Operation<StringHandler> op)
	{
		StringType type = new StringType();
		Serializer<Operation<StringHandler>> serializer = type.getSerializer();
		SerializerTestHelper.testStatic(json, serializer, op);
	}
}
