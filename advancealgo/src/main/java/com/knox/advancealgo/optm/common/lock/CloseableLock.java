package com.knox.advancealgo.optm.common.lock;

/**
 * Lock adapter that is designed to be used with try-statements.
 *
 * @author Andreas Holstenson
 *
 */
public interface CloseableLock
	extends AutoCloseable
{
	/**
	 * Release this lock.
	 */
	@Override
	void close();
}
