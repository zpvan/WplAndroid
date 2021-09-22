package com.knox.advancealgo.optm.operations.combined;

import org.eclipse.collections.api.factory.Maps;
import org.eclipse.collections.api.map.MutableMap;

import com.knox.advancealgo.optm.operations.OTType;
import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.list.ListType;
import com.knox.advancealgo.optm.operations.map.MapType;
import com.knox.advancealgo.optm.operations.string.StringType;

/**
 * Builder for instances of {@link CombinedType}.
 *
 * @author Andreas Holstenson
 *
 */
public class CombinedTypeBuilder
{
	private final MutableMap<String, OTType<Operation<?>>> types;

	public CombinedTypeBuilder()
	{
		types = Maps.mutable.empty();

		withSubType("map", new MapType());
		withSubType("list", new ListType());
		withSubType("string", new StringType());
	}

	public <Op extends Operation<?>> CombinedTypeBuilder withSubType(String id, OTType<Op> type)
	{
		types.put(id, (OTType) type);

		return this;
	}

	public CombinedType build()
	{
		return new CombinedType(types.toImmutable());
	}
}
