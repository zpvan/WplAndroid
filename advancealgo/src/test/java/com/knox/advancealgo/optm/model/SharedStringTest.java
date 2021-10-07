package com.knox.advancealgo.optm.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.knox.advancealgo.optm.engine.LocalOperationSync;
import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.combined.CombinedHandler;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SharedStringTest
{
	private LocalOperationSync<Operation<CombinedHandler>> sync;

	@Before
	public void before()
	{
		sync = ModelTestHelper.createSync();
	}

	@After
	public void close()
		throws IOException
	{
		sync.close();
	}

	public Model model()
	{
		return ModelTestHelper.createModel(sync);
	}

	@Test
	public void testInit()
	{
		Model m = model();

		SharedString string = m.newString();
		assertThat(string, notNullValue());
	}


	/**
	 * Test that several concurrent appends resolve to the same string value.
	 */
	@Test
	public void testConcurrentAppend()
	{
		Model m1 = model();
		Model m2 = model();

		SharedString string1 = m1.newString();
		m1.set("string", string1);

		sync.waitForEmpty();

		SharedString string2 = m2.get("string");

		sync.suspend();

		string1.append("a");
		string2.append("b");

		sync.resume();

		sync.waitForEmpty();

		assertThat(string1.get(), is(string2.get()));
	}

	@Test
	public void testConcurrentEdit()
	{
		Model m1 = model();
		Model m2 = model();

		SharedString string1 = m1.newString();
		m1.set("string", string1);

		sync.waitForEmpty();

		SharedString string2 = m2.get("string");

		sync.suspend();

		string1.append("a");
		string2.append("b");

		sync.resume();

		sync.waitForEmpty();

		assertThat(string1.get(), is(string2.get()));

		sync.suspend();

		string1.remove(0, 1);
		string1.insert(1, "cd");
		string2.remove(1, 2);

		sync.resume();

		sync.waitForEmpty();

		ModelLogHelper.e("testConcurrentEdit", string1, string2);
	}
}
