package com.knox.advancealgo.optm.model.spi;

import com.knox.advancealgo.optm.common.lock.CloseableLock;
import com.knox.advancealgo.optm.model.SharedObject;
import com.knox.advancealgo.optm.operations.Operation;

/**
 * Helper for implementations of {@link SharedObject}, contains id, type,
 * latest value and a method that can should be called when an operation is
 * performed locally on the object.
 *
 * @author Andreas Holstenson
 *
 */
public interface SharedObjectEditor<Op extends Operation<?>>
{
	/**
	 * Get the identifier being used.
	 *
	 * @return
	 */
	String getId();

	/**
	 * Get the type being used.
	 *
	 * @return
	 */
	String getType();

	/**
	 * Get the current value.
	 *
	 * @return
	 */
	Op getCurrent();

	/**
	 * Acquire a lock on this editor, which guarantees that no other
	 * changes will occur while the lock is held.
	 *
	 * @return
	 */
	CloseableLock lock();

	/**
	 * Locally apply the given operation.
	 *
	 * @param op
	 */
	void apply(Op op);

	/**
	 * Queue a runnable that should trigger an event when the current lock
	 * is released or when the current remote change is fully applied.
	 *
	 * @param runnable
	 */
	void queueEvent(Runnable runnable);

	/**
	 * Get an object from the model.
	 *
	 * @param id
	 * @param type
	 * @return
	 */
	SharedObject getObject(String id, String type);

	/**
	 * Set a handler that will receive any operations that occur.
	 *
	 * @param handler
	 */
	void setOperationHandler(OperationHandler<Op> handler);

	interface OperationHandler<Op>
	{
		void newOperation(Op op, boolean local);
	}
}
