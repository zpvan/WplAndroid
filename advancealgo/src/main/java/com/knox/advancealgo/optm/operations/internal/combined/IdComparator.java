package com.knox.advancealgo.optm.operations.internal.combined;

import java.util.Comparator;

import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.combined.CombinedHandler;

/**
 * Comparator that sorts {@link CombinedOperation} by their identifier.
 *
 * @author Andreas Holstenson
 *
 */
public class IdComparator
	implements Comparator<Operation<CombinedHandler>>
{
	public static IdComparator INSTANCE = new IdComparator();

	private IdComparator()
	{
	}

	@Override
	public int compare(Operation<CombinedHandler> o1, Operation<CombinedHandler> o2)
	{
		return ((CombinedOperation) o1).getId().compareTo(((CombinedOperation) o2).getId());
	}
}
