package com.knox.advancealgo.optm.operations.combined;

import com.knox.advancealgo.optm.operations.Operation;

public interface CombinedHandler
{
	void update(String id, String type, Operation<?> change);
}
