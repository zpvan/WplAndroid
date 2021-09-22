package com.knox.advancealgo.optm.engine;

import com.knox.advancealgo.optm.engine.events.ChangeEvent;
import com.knox.advancealgo.optm.operations.Operation;

/**
 * Listener used by {@link Editor}.
 *
 * @author Andreas Holstenson
 *
 */
public interface EditorListener<Op extends Operation<?>>
{
	/**
	 * The editor has applied either a remote or local event.
	 *
	 * @param event
	 */
	void editorChanged(ChangeEvent<Op> event);
}
