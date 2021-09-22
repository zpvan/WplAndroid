package com.knox.advancealgo.optm.model;

import com.knox.advancealgo.optm.engine.DefaultEditor;
import com.knox.advancealgo.optm.engine.DefaultEditorControl;
import com.knox.advancealgo.optm.engine.InMemoryOperationHistory;
import com.knox.advancealgo.optm.engine.LocalOperationSync;
import com.knox.advancealgo.optm.engine.OperationSync;
import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.combined.CombinedDelta;
import com.knox.advancealgo.optm.operations.combined.CombinedHandler;
import com.knox.advancealgo.optm.operations.combined.CombinedType;
import com.knox.advancealgo.optm.operations.combined.CombinedTypeBuilder;

public class ModelTestHelper
{
	private static final CombinedType TYPE = new CombinedTypeBuilder()
		.build();

	public static LocalOperationSync<Operation<CombinedHandler>> createSync()
	{
		DefaultEditorControl<Operation<CombinedHandler>> control = new DefaultEditorControl<>(
			new InMemoryOperationHistory<>(TYPE, CombinedDelta.builder().done()
		));
		return new LocalOperationSync<>(control);
	}

	public static Model createModel(OperationSync<Operation<CombinedHandler>> sync)
	{
		return Model.builder(new DefaultEditor<>(sync)).build();
	}
}
