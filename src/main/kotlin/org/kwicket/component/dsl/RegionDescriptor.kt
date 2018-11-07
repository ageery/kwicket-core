package org.kwicket.component.dsl

/**
 * Describes how to create a Wicket "region" (e.g., Panel or Border): the HTML plus how to panel the associated components.
 *
 * @property markup the HTML that the Wicket components attach to
 * @property builders builders for creating Wicket components that attach to the markup
 */
class RegionDescriptor(
    val markup: String,
    val builders: List<RegionItem>
)