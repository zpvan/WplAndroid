package com.knox.advancealgo.optm.operations.map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.eclipse.collections.api.factory.Lists;
import org.junit.Test;

import com.knox.advancealgo.optm.operations.CompoundOperation;
import com.knox.advancealgo.optm.operations.DefaultCompoundOperation;
import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.OperationPair;
import com.knox.advancealgo.optm.operations.internal.map.MapSet;

public class MapTransformTest
{
	@Test
	public void testDifferentKeys()
	{
		reversibleTest(
			set("one", null, "abc"),
			set("two", null, "def"),
			of(set("one", null, "abc")),
			of(set("two", null, "def"))
		);
	}

	@Test
	public void testSameKey()
	{
		test(
			set("one", null, "abc"),
			set("one", null, "def"),
			of(),
			of(set("one", "abc", "def"))
		);
	}

	@Test
	public void testSeveralSameKey1()
	{
		test(
			of(set("one", null, "abc"), set("two", null, "kaka")),
			set("one", null, "def"),
			of(set("two", null, "kaka")),
			of(set("one", "abc", "def"))
		);
	}

	@Test
	public void testSeveralSameKey2()
	{
		test(
			of(set("two", null, "abc"), set("one", null, "kaka")),
			set("two", null, "def"),
			of(set("one", null, "kaka")),
			of(set("two", "abc", "def"))
		);
	}


	private static Operation<MapHandler> set(String key, Object oldValue, Object newValue)
	{
		return new MapSet(key, oldValue, newValue);
	}

	private static CompoundOperation<MapHandler> of(Operation<MapHandler>... ops)
	{
		return new DefaultCompoundOperation<>(Lists.immutable.of(ops));
	}

	private void test(Operation<?> client, Operation<?> server,
			Operation<?> expectedClient, Operation<?> expectedServer)
	{
		MapType type = new MapType();
		OperationPair op = type.transform((Operation) client, (Operation) server);
		assertThat("left", op.getLeft(), is(expectedClient));
		assertThat("right", op.getRight(), is(expectedServer));
	}

	private void reversibleTest(Operation<?> client, Operation<?> server,
			Operation<?> expectedClient, Operation<?> expectedServer)
	{
		test(client, server, expectedClient, expectedServer);
		test(server, client, expectedServer, expectedClient);
	}

}
