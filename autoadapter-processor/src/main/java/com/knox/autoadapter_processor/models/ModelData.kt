package com.knox.autoadapter_processor.models

/**
 *   This will be the model class containing all the information
 * required to generate the adapter.
 *
 * 1. You need to know the package name so that the Adapter source file
 *    live in the same package as the source model.
 * 2. You'll use the name of the model to construct the name for the
 *    Adapter class.
 * 3. The layout ID parameter will be extracted from the AdapterModel
 *    annotation.
 * 4. The list of ViewHolderBindingData instances if for the fields of
 *    the model class.
 */
data class ModelData(
    val packageName: String, // 1
    val modelName: String, // 2
    val layoutId: Int, // 3
    val viewHolderBindingData: List<ViewHolderBindingData> // 4
)