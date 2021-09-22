package com.knox.advancealgo.optm.model.internal;

import com.knox.advancealgo.optm.common.EventHelper;
import com.knox.advancealgo.optm.common.lock.CloseableLock;
import com.knox.advancealgo.optm.model.SharedMap;
import com.knox.advancealgo.optm.model.spi.AbstractSharedObject;
import com.knox.advancealgo.optm.model.spi.DataValues;
import com.knox.advancealgo.optm.model.spi.SharedObjectEditor;
import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.map.MapDelta;
import com.knox.advancealgo.optm.operations.map.MapHandler;

import java.util.HashMap;
import java.util.Map;


public class SharedMapImpl
	extends AbstractSharedObject<Operation<MapHandler>>
	implements SharedMap
{
	private final Map<String, Object> values;

	private final MapHandler            handler;
	private final EventHelper<Listener> changeListeners;

	public SharedMapImpl(SharedObjectEditor<Operation<MapHandler>> editor)
	{
		super(editor);

		values = new HashMap<>();

		changeListeners = new EventHelper<>();
		handler = createHandler();

		editor.getCurrent().apply(handler);
		editor.setOperationHandler(this::apply);
	}

	private MapHandler createHandler()
	{
		return new MapHandler()
		{
			@Override
			public void remove(String key, Object oldValue)
			{
				Object old = values.remove(key);
				editor.queueEvent(() -> changeListeners.trigger(l ->
					l.valueRemoved(key, old)
				));
			}

			@Override
			public void put(String key, Object oldValue, Object newValue)
			{
				Object value = DataValues.fromData(editor, newValue);
				Object old = values.put(key, value);
				editor.queueEvent(() ->
					changeListeners.trigger(l ->
						l.valueChanged(key, old, value)
					)
				);
			}
		};
	}

	private void apply(Operation<MapHandler> op, boolean local)
	{
		op.apply(handler);
	}

	@Override
	public String getObjectId()
	{
		return editor.getId();
	}

	@Override
	public String getObjectType()
	{
		return editor.getType();
	}

	@Override
	public boolean containsKey(String key)
	{
		return values.containsKey(key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(String key)
	{
		return (T) values.get(key);
	}

	@Override
	public void remove(String key)
	{
		try(CloseableLock lock = editor.lock())
		{
			Object value = values.get(key);
			editor.apply(MapDelta.builder()
				.set(key, DataValues.toData(value), null)
				.done()
			);
		}
	}

	@Override
	public void set(String key, Object value)
	{
		if(value == null)
		{
			throw new IllegalArgumentException("null values are currently not supported");
		}

		try(CloseableLock lock = editor.lock())
		{
			Object old = values.get(key);
			editor.apply(MapDelta.builder()
				.set(key, DataValues.toData(old), DataValues.toData(value))
				.done()
			);
		}
	}

	@Override
	public void addChangeListener(Listener listener)
	{
		changeListeners.add(listener);
	}

	@Override
	public void removeChangeListener(Listener listener)
	{
		changeListeners.remove(listener);
	}
}
