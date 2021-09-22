package com.knox.advancealgo.optm.operations.internal.map;

import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.map.MapHandler;

public interface MapOperation
	extends Operation<MapHandler>
{
	/**
	 * Get the key this operation acts upon.
	 *
	 * @return
	 */
	String getKey();
}
