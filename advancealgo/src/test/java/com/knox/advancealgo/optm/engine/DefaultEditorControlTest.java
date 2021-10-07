package com.knox.advancealgo.optm.engine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.knox.advancealgo.optm.OpLogHelper;
import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.string.StringDelta;
import com.knox.advancealgo.optm.operations.string.StringHandler;
import com.knox.advancealgo.optm.operations.string.StringType;

import org.junit.Before;
import org.junit.Test;

public class DefaultEditorControlTest
{
	private EditorControl<Operation<StringHandler>> control;

	@Before
	public void setupEditorControl()
	{
		control = new DefaultEditorControl<>(
			new InMemoryOperationHistory<>(new StringType(), StringDelta.builder()
				.insert("Hello World")
				.done()
			)
		);
	}

	@Test
	public void testGetLatest()
	{
		TaggedOperation<Operation<StringHandler>> latest = control.getLatest();
		assertThat(latest.getOperation(), is(StringDelta.builder()
			.insert("Hello World")
			.done()
		));
	}

	@Test
	public void testEdit()
	{
		long historyId = control.getLatest().getHistoryId();
		control.store(historyId, "1", StringDelta.builder()
			.retain(6)
			.delete("World")
			.insert("Cookie")
			.done()
		);

		TaggedOperation<Operation<StringHandler>> latest = control.getLatest();
		/**
		 * Editor的断言，判断的是low-level api，如果应用呢？
		 */
		assertThat(latest.getOperation(), is(StringDelta.builder()
			.insert("Hello Cookie")
			.done()
		));
	}

	@Test
	public void testEditsFromSameHistoryId()
	{
		long historyId = control.getLatest().getHistoryId();
		control.store(historyId, "1", StringDelta.builder()
			.retain(6)
			.delete("World")
			.insert("Cookie")
			.done()
		);

		control.store(historyId, "1", StringDelta.builder()
			.retain(11)
			.insert("!")
			.done()
		);

		TaggedOperation<Operation<StringHandler>> latest = control.getLatest();

		OpLogHelper.e("testEditsFromSameHistoryId", latest.getOperation());

		assertThat(latest.getOperation(), is(StringDelta.builder()
			.insert("Hello Cookie!")
			.done()
		));
	}
}
