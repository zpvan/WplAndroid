package com.knox.advancealgo.optm.operations.combined;

import org.junit.Test;

import se.l4.exobytes.Serializer;
import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.SerializerTestHelper;
import com.knox.advancealgo.optm.operations.map.MapDelta;
import com.knox.advancealgo.optm.operations.string.StringDelta;

public class CombinedSerializationTest
{
	private static CombinedType TYPE = new CombinedTypeBuilder().build();

	@Test
	public void test1()
	{
		test(CombinedDelta.builder()
			.update("one", "string", StringDelta.builder()
				.insert("Hello World")
				.done())
			.done()
		);
	}

	@Test
	public void test2()
	{
		test(CombinedDelta.builder()
			.update("one", "map", MapDelta.builder()
				.set("key", null, "abc")
				.done())
			.done()
		);
	}

	@Test
	public void test3()
	{
		test(CombinedDelta.builder()
			.update("one", "map", MapDelta.builder()
				.set("key", null, "abc")
				.done())
			.update("two", "string", StringDelta.builder()
				.insert("Hello World")
				.done())
			.done()
		);
	}

	@Test
	public void test4()
	{
		test("[[\"update\",\"one\",\"string\",[[\"insert\",\"Hello World\"]]]]", CombinedDelta.builder()
			.update("one", "string", StringDelta.builder()
				.insert("Hello World")
				.done())
			.done()
		);
	}

	private void test(Operation<CombinedHandler> op)
	{
		Serializer<Operation<CombinedHandler>> serializer = TYPE.getSerializer();
		SerializerTestHelper.testSymmetry(serializer, op);
	}

	private void test(String json, Operation<CombinedHandler> op)
	{
		Serializer<Operation<CombinedHandler>> serializer = TYPE.getSerializer();
		SerializerTestHelper.testStatic(json, serializer, op);
	}
}
