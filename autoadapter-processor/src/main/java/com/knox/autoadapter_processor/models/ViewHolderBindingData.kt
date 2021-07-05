package com.knox.autoadapter_processor.models

/**
 *   This model stores information that will be extracted from the
 * ViewHolderBinding annotation.
 *
 * 1. The name of the model field annotated with ViewHolderBinding.
 * 2. The view ID parameter of the ViewHolderBinding annotation.
 */
data class ViewHolderBindingData(
    val fieldName: String, // 1
    val viewId: Int // 2
)