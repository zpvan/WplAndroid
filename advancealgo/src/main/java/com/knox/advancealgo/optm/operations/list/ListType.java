package com.knox.advancealgo.optm.operations.list;

import se.l4.exobytes.Serializer;
import com.knox.advancealgo.optm.operations.CompoundOperation;
import com.knox.advancealgo.optm.operations.OTType;
import com.knox.advancealgo.optm.operations.Operation;
import com.knox.advancealgo.optm.operations.OperationPair;
import com.knox.advancealgo.optm.operations.internal.list.ListOperationSerializer;
import com.knox.advancealgo.optm.operations.internal.list.ListTypeComposer;
import com.knox.advancealgo.optm.operations.internal.list.ListTypeTransformer;
import com.knox.advancealgo.optm.operations.string.StringType;

/**
 * Operation Transformation type for lists. Use {@link ListDelta} to construct
 * operations.
 *
 * <p>
 * The support for list is very similar to {@link StringType strings}. It
 * uses three operations, retain, insert and delete.
 *
 * @author Andreas Holstenson
 *
 */
public class ListType
	implements OTType<Operation<ListHandler>>
{
	@Override
	public Operation<ListHandler> compose(Operation<ListHandler> left, Operation<ListHandler> right)
	{
		return new ListTypeComposer(
			CompoundOperation.toList(left),
			CompoundOperation.toList(right)
		).perform();
	}

	@Override
	public OperationPair<Operation<ListHandler>> transform(Operation<ListHandler> left, Operation<ListHandler> right)
	{
		return new ListTypeTransformer(
			left,
			right
		).perform();
	}

	@Override
	public Serializer<Operation<ListHandler>> getSerializer()
	{
		return ListOperationSerializer.INSTANCE;
	}

}
