package com.knox.autoadapter_processor.codegen

import com.knox.autoadapter_processor.models.ModelData
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

class AdapterCodeBuilder(
    private val adapterName: String,
    private val data: ModelData
) {
    /**
     * 1. ViewHolder will be a nested class, RecyclerView.ViewHolder, implementation
     *    inside the generated adapter.
     * 2. Its ClassName contains both its package and its name.
     * 3. You need the fully qualified name as RecyclerView.Adapter is parameterized.
     * 4. The ClassName for the model class this adapter is being created for.
     * 5. The adapter's sole field is a List of items to render, all of which are of
     *    "model" type.
     * 6. You need TextView to be able to bind String to them later on.
     */
    private val viewHolderName = "ViewHolder" // 1
    private val viewHolderClassName = ClassName(data.packageName, viewHolderName) // 2
    private val viewHolderQualifiedClassName = ClassName(data.packageName,
        adapterName + ".$viewHolderName") // 3
    private val modelClassName = ClassName(data.packageName, data.modelName) // 4
    private val itemsListClassName = ClassName("kotlin.collections", "List") // 5
        .parameterizedBy(modelClassName)
    private val textViewClassName = ClassName("android.widget", "TextView") // 6

    /**
     * 1. You're building a type whose name is adapterName.
     * 2. Is has a primary constructor with a single parameter named items of type
     *    itemsListClassName.
     * 3. Your adapter extends RecyclerView.Adapter, and ViewHolder is of type
     *    viewHolderQualifiedClassName.
     * 4. The adapter has a private property named items, which is initialized by
     *    the constructor parameter with the same name. This will result in a
     *    private val inside the generated adapter.
     */
    fun build(): TypeSpec = TypeSpec.classBuilder(adapterName) // 1
        .primaryConstructor(FunSpec.constructorBuilder() // 2
            .addParameter("items", itemsListClassName)
            .build()
        )
        // 3
        .superclass(ClassName("androidx.recyclerview.widget.RecyclerView", "Adapter")
            .parameterizedBy(viewHolderQualifiedClassName)
        )
        .addProperty(PropertySpec.builder("items", itemsListClassName) // 4
            .addModifiers(KModifier.PRIVATE)
            .initializer("items")
            .build()
        )
        // TODO More to come here
        .addBaseMethods()
        .addViewHolderType()
        .build()

    /**
     * 1. addBaseMethods is an extension on TypeSpec.Builder that performs the
     *    following actions on it.
     * 2. Add a new method to the class named getItemCount.
     * 3. The method overrides an abstract method.
     * 4. It returns an Int.
     * 5. It contains a single return statement, returning the size of list.
     */
    private fun TypeSpec.Builder.addBaseMethods(): TypeSpec.Builder = apply { // 1
        addFunction(FunSpec.builder("getItemCount") // 2
            .addModifiers(KModifier.OVERRIDE) // 3
            .returns(INT) // 4
            .addStatement("return items.size") // 5
            .build()
        )

        // TODO MORE
        addFunction(FunSpec.builder("onCreateViewHolder")
            /**
             * 1. addParameter adds parameters to function definitions. For example,
             *    the onCreateViewHolder method you're overriding has two parameters:
             *    parent and viewType.
             * 2. KotlinPoet has its own string formatting flags. Be sure to check
             *    them out.
             */
            .addModifiers(KModifier.OVERRIDE)
            .addParameter("parent", ClassName("android.view", "ViewGroup")) // 1
            .addParameter("viewType", INT)
            .returns(viewHolderQualifiedClassName)
            .addStatement("val view = " +
                "android.view.LayoutInflater.from(parent.context).inflate(%L, " +
                "parent, false)", data.layoutId) // 2
            .addStatement("return $viewHolderName(view)")
            .build()
        )

        addFunction(FunSpec.builder("onBindViewHolder")
            .addModifiers(KModifier.OVERRIDE)
            .addParameter("viewHolder", viewHolderQualifiedClassName)
            .addParameter("position", INT)
            .addStatement("viewHolder.bind(items[position])")
            .build()
        )
    }

    private fun TypeSpec.Builder.addViewHolderType(): TypeSpec.Builder = addType(
        TypeSpec.classBuilder(viewHolderClassName)
            .primaryConstructor(FunSpec.constructorBuilder()
                .addParameter("itemView", ClassName("android.view", "View"))
                .build()
            )
            .superclass(ClassName(
                "androidx.recyclerview.widget.RecyclerView",
                "ViewHolder")
            )
            .addSuperclassConstructorParameter("itemView")
            // TODO binding
            .addBindingMethod()
            .build()
    )

    /**
     * 1. The new method's name is bind. It takes a single parameter, a model
     *    instance, to bind.
     * 2. Iterate through the collected ModelData's viewHolderBindingData list.
     * 3. For each model property annotated with ViewHolderBindingData, output
     *    a statement that:
     *    * Finds a TextView for the given viewId.
     *    * Sets its text property to the model instance's property.
     */
    private fun TypeSpec.Builder.addBindingMethod(): TypeSpec.Builder = addFunction(
        FunSpec.builder("bind") // 1
            .addParameter("item", modelClassName)
            .apply {
                data.viewHolderBindingData.forEach { // 2
                    addStatement("itemView.findViewById<%T>(%L).text = item.%L",
                        textViewClassName, it.viewId, it.fieldName) // 3
                }
            }
            .build()
    )
}