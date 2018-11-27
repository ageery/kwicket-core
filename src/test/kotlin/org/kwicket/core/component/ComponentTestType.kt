package org.kwicket.core.component

enum class ComponentTestType {
    Disabled,
    Enabled,
    Invisible,
    Visible,
    VisibilityAllowed,
    VisibilityNotAllowed,
    NonEscapedModelStrings,
    EscapedModelStrings,
    RenderBodyOnly,
    RenderEnclosingTag,
    ExplicitMarkupId,
    ImplicitMarkupId,
    NoMarkupId,
    PlaceholderTag,
    InvisiblePlaceholderTag,
    SingleBehavior,
    OneBehaviors,
    MultiBehaviors,
    BehaviorWithBehaviors,
    OnConfigInvisible,
    InvisibleByBlock
}