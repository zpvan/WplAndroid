package com.knox.advancealgo.optm.operations.internal.combined;

import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.combined.CombinedHandler;

public interface CombinedOperation
	extends Operation<CombinedHandler>
{
	/**
	 * Get the identifier that the operation targets.
	 *
	 * @return
	 */
	String getId();
}
