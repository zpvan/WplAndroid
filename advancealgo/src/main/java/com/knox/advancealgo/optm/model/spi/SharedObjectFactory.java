package com.knox.advancealgo.optm.model.spi;

import com.knox.advancealgo.optm.model.SharedObject;
import com.knox.advancealgo.optm.operations.Operation;

/**
 * Factory for {@link SharedObject}s.
 *
 * @author Andreas Holstenson
 *
 * @param <T>
 * @param <Op>
 */
public interface SharedObjectFactory<T extends SharedObject, Op extends Operation<?>>
{
	/**
	 * Create an object that uses the specified editor.
	 *
	 * @param editor
	 * @return
	 */
	T create(SharedObjectEditor<Op> editor);
}
