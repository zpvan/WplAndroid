package com.knox.advancealgo.optm.model.internal;

import com.knox.advancealgo.optm.model.DefaultModel;
import com.knox.advancealgo.optm.model.Model;
import com.knox.advancealgo.optm.model.ModelBuilder;
import com.knox.advancealgo.optm.model.SharedObject;
import com.knox.advancealgo.optm.model.spi.SharedObjectEditor;
import com.knox.advancealgo.optm.model.spi.SharedObjectFactory;
import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.combined.CombinedHandler;

import java.util.HashMap;
import java.util.Map;

import com.knox.advancealgo.optm.engine.Editor;


public class DefaultModelBuilder
	implements ModelBuilder
{
	private final Editor<Operation<CombinedHandler>>     editor;
	private final Map<String, SharedObjectFactory<?, ?>> types;

	public DefaultModelBuilder(Editor<Operation<CombinedHandler>> editor)
	{
		this.editor = editor;

		types = new HashMap<>();

		addType("map", SharedMapImpl::new);
		addType("string", SharedStringImpl::new);
		addType("list", e -> new SharedListImpl<Object>((SharedObjectEditor) e));
	}

	@Override
	public <T extends SharedObject, Op extends Operation<?>> ModelBuilder addType(String id, SharedObjectFactory<T, Op> factory)
	{
		types.put(id, factory);
		return this;
	}

	@Override
	public Model build()
	{
		return new DefaultModel(editor, types);
	}
}
