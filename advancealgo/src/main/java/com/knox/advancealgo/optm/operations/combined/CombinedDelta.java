package com.knox.advancealgo.optm.operations.combined;

import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.internal.combined.DefaultCombinedDelta;

/**
 * Delta builder for combined operations.
 *
 * @author Andreas Holstenson
 *
 * @param <ReturnPath>
 */
public interface CombinedDelta<ReturnPath>
{
	/**
	 * Update the object with the given id with the specified operation.
	 *
	 * @param id
	 * @param type
	 * @param op
	 * @return
	 */
	CombinedDelta<ReturnPath> update(String id, String type, Operation<?> op);

	/**
	 * Indicate that we are done building this delta.
	 *
	 * @return
	 */
	ReturnPath done();


	/**
	 * Get {@link CombinedDelta} that builds a {@link Operation}.
	 *
	 * @return
	 */
	static CombinedDelta<Operation<CombinedHandler>> builder()
	{
		return new DefaultCombinedDelta<>(o -> o);
	}
}
