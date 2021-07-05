package com.knox.autoadapter_processor

import com.knox.autoadapter_annotations.AdapterModel
import com.knox.autoadapter_annotations.ViewHolderBinding
import com.knox.autoadapter_processor.codegen.AdapterCodeBuilder
import com.knox.autoadapter_processor.models.ModelData
import com.knox.autoadapter_processor.models.ViewHolderBindingData
import com.squareup.kotlinpoet.FileSpec
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

/**
 * 1. @SupportSourceVersion specifies that this processor supports Java 8.
 * 2. All annotation processors must extend the AbstractProcessor class.
 * 3. getSupportAnnotationTypes() defines a set of annotations this processor
 *    looks up when running. If no elements in the target module are annotated
 *    with annotation from this set, the processor won't run.
 * 4. process is the core method that gets called in every annotation-processing
 *    round.
 * 5. process must return true if everything went fine.
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8) // 1
class Processor : AbstractProcessor() { // 2

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    override fun getSupportedAnnotationTypes() =
        mutableSetOf(AdapterModel::class.java.canonicalName) // 3

    override fun process(annotations: MutableSet<out TypeElement>?,
                         roundEnv: RoundEnvironment?): Boolean { // 4
        val kaptKotlinGeneratedDir = processingEnv
            .options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
            ?: return false
        /**
         * Processing the Annotations
         *
         * =>1 Extract all code elements annotated with AdapterModel.
         * =>2 Iterate through all those elements.
         * =>3 Extract model data for each element.
         */
        roundEnv?.getElementsAnnotatedWith(AdapterModel::class.java) // =>1
            ?.forEach { // =>2
                val modelData = getModelData(it) // =>3
                // TODO More to come here
                /**
                 * 1. The filename of the adapter class will be whatever the name of
                 *    the model class is, suffixed by "Adapter".
                 * 2. Create a new file in the same package as the model class, and
                 *    name it fileName.
                 * 3. Add a new type to it by running an AdapterCodeBuilder. This adds
                 *    the adapter as the content of the file, using all the code you've
                 *    written before!
                 * 4. Write the generated file to the kaptKotlinGeneratedDir folder.
                 */
                val fileName = "${modelData.modelName}Adapter" // ==>1
                FileSpec.builder(modelData.packageName, fileName) // ==>2
                    .addType(AdapterCodeBuilder(fileName, modelData).build()) // ==>3
                    .build()
                    .writeTo(File(kaptKotlinGeneratedDir)) // ==>4
            }


        return true // 5
    }

    /**
     *   Extract all the relevant information from the annotated code element
     * and build up the models you're created in the previous section.
     *
     * 1. Extracts the package name from the element.
     * 2. Gets the class name of the model that the annotation was present on.
     * 3. Gets the annotation itself.
     * 4. Extracts the layoutId parameter from the annotation.
     * 5. Iterates through all the element's enclosed (child) elements. The
     *    top-level element here is the model class, and its children are
     *    its properties.
     * 6. Checks if the child element is annotated with ViewHolderBinding.
     * 7. If it isn't, skips it.
     * 8. Otherwise, collects the child element's name and viewId from its
     *    annotation.
     * 9. Packs all this info into a ModelData instance.
     */
    private fun getModelData(elem: Element): ModelData {
        val packageName = processingEnv.elementUtils.getPackageOf(elem).toString() // 1
        val modelName = elem.simpleName.toString() // 2
        val annotation = elem.getAnnotation(AdapterModel::class.java) // 3
        val layoutId = annotation.layoutId // 4
        val viewHolderBindingData = elem.enclosedElements.mapNotNull {
            val viewHolderBinding = it.getAnnotation(ViewHolderBinding::class.java) // 6
            if (viewHolderBinding == null) {
                null // 7
            } else {
                val elementName = it.simpleName.toString()

                /**
                 * elementName => getName$annotations
                 * .substring(3, elementName.indexOf('$')) => Name
                 * .lowercase() => name
                 *
                 * getField$annotations => field
                 */
                val fieldName = elementName.substring(3, elementName.indexOf('$')).lowercase()
                ViewHolderBindingData(fieldName, viewHolderBinding.viewId) // 8
            }
        }
        return ModelData(packageName, modelName, layoutId, viewHolderBindingData) // 9
    }
}