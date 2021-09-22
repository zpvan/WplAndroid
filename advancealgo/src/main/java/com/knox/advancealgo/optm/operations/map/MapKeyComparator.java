package com.knox.advancealgo.optm.operations.map;

import java.util.Comparator;

import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.internal.map.MapOperation;

public class MapKeyComparator
	implements Comparator<Operation<MapHandler>>
{
	public static MapKeyComparator INSTANCE = new MapKeyComparator();

	private MapKeyComparator()
	{
	}

	@Override
	public int compare(Operation<MapHandler> o1, Operation<MapHandler> o2)
	{
		return ((MapOperation) o1).getKey().compareTo(((MapOperation) o2).getKey());
	}
}
