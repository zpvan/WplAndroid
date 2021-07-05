package com.knox.autoadapter_annotations

/**
 * 1. Unlike AdapterModel, this one will exclusively target properties.
 * 2. It only needs to be around during the compilation phase.
 * 3. Its sole parameter specifies the ID of the view that the annotated
 *    property should bind to.
 */
@Target(AnnotationTarget.PROPERTY) // 1
@Retention(AnnotationRetention.SOURCE) // 2
annotation class ViewHolderBinding(val viewId: Int) // 3
