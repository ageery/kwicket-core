package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.upload.FileUpload
import org.apache.wicket.markup.html.form.upload.FileUploadField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.builder.FileUploadFieldBuilder
import org.kwicket.component.builder.IFileUploadFieldBuilder
import org.kwicket.component.dsl.ComponentTag

fun HTMLTag.fileUploadField(
    id: String? = null,
    tagName: String = "input",
    label: IModel<String>? = null,
    validator: IValidator<MutableList<FileUpload>>? = null,
    isRequired: Boolean? = null,
    validators: List<IValidator<MutableList<FileUpload>>>? = null,
    model: IModel<MutableList<FileUpload>>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    enabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    initialAttributes: Map<String, String> = mapOf("type" to "file"),
    block: FileUploadFieldTag.() -> Unit = {}
): Unit =
    FileUploadFieldTag(
        id = id,
        tagName = tagName,
        label = label,
        isRequired = isRequired,
        validator = validator,
        validators = validators,
        model = model,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        visibilityAllowed = visibilityAllowed,
        enabled = enabled,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class FileUploadFieldTag(
    id: String? = null,
    tagName: String = "input",
    initialAttributes: Map<String, String> = mapOf("type" to "file"),
    consumer: TagConsumer<*>,
    val builder: FileUploadFieldBuilder
) : IFileUploadFieldBuilder by builder,
    ComponentTag<FileUploadField>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName
    ), HtmlBlockTag {

    constructor(
        id: String? = null,
        label: IModel<String>? = null,
        isRequired: Boolean? = null,
        validator: IValidator<MutableList<FileUpload>>? = null,
        validators: List<IValidator<MutableList<FileUpload>>>? = null,
        tagName: String = "input",
        initialAttributes: Map<String, String> = mapOf("type" to "file"),
        consumer: TagConsumer<*>,
        model: IModel<MutableList<FileUpload>>? = null,
        markupId: String? = null,
        outputMarkupId: Boolean? = null,
        outputMarkupPlaceholderTag: Boolean? = null,
        visible: Boolean? = null,
        visibilityAllowed: Boolean? = null,
        enabled: Boolean? = null,
        escapeModelStrings: Boolean? = null,
        renderBodyOnly: Boolean? = null,
        behavior: Behavior? = null,
        behaviors: List<Behavior>? = null
    ) : this(
        id = id,
        tagName = tagName,
        initialAttributes = initialAttributes,
        consumer = consumer,
        builder = FileUploadFieldBuilder(
            model = model,
            validators = validators,
            validator = validator,
            label = label,
            isRequired = isRequired,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            isVisible = visible,
            isVisibilityAllowed = visibilityAllowed,
            isEnabled = enabled,
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors
        )
    )

    override fun build(id: String) = builder.build(id)
}