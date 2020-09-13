package com.reactnativenavigation.options

import android.animation.AnimatorSet
import android.util.Property
import android.view.View
import com.reactnativenavigation.options.params.Bool
import com.reactnativenavigation.options.params.NullBool
import com.reactnativenavigation.options.params.NullText
import com.reactnativenavigation.options.params.Text
import com.reactnativenavigation.options.parsers.BoolParser
import com.reactnativenavigation.options.parsers.TextParser
import com.reactnativenavigation.utils.CollectionUtils
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

open class AnimationOptions(json: JSONObject?) {
    @JvmField var id: Text = NullText()
    @JvmField var enabled: Bool = NullBool()
    @JvmField var waitForRender: Bool = NullBool()
    @JvmField var elementTransitions = ElementTransitions()
    @JvmField var sharedElements = SharedElements()
    private var valueOptions = HashSet<ValueAnimationOptions>()

    constructor() : this(null)

    init {
        json?.let { parse(it) }
    }

    internal fun parse(json: JSONObject?) {
        json?.let {
            val iter = json.keys()
            while (iter.hasNext()) {
                when (val key = iter.next()) {
                    "id" -> id = TextParser.parse(json, key)
                    "enable", "enabled" -> enabled = BoolParser.parse(json, key)
                    "waitForRender" -> waitForRender = BoolParser.parse(json, key)
                    "elementTransitions" -> elementTransitions = ElementTransitions.parse(json)
                    "sharedElementTransitions" -> sharedElements = SharedElements.parse(json)
                    else -> valueOptions.add(ValueAnimationOptions.parse(json.optJSONObject(key), ViewProperty.create(key)))
                }
            }
        }
    }

    fun mergeWith(other: AnimationOptions) {
        if (other.id.hasValue()) id = other.id
        if (other.enabled.hasValue()) enabled = other.enabled
        if (other.waitForRender.hasValue()) waitForRender = other.waitForRender
        if (other.valueOptions.isNotEmpty()) valueOptions = other.valueOptions
        if (other.elementTransitions.hasValue()) elementTransitions = other.elementTransitions
        if (other.sharedElements.hasValue()) sharedElements = other.sharedElements
    }

    fun mergeWithDefault(defaultOptions: AnimationOptions) {
        if (!id.hasValue()) id = defaultOptions.id
        if (!enabled.hasValue()) enabled = defaultOptions.enabled
        if (!waitForRender.hasValue()) waitForRender = defaultOptions.waitForRender
        if (valueOptions.isEmpty()) valueOptions = defaultOptions.valueOptions
        if (!elementTransitions.hasValue()) elementTransitions = defaultOptions.elementTransitions
        if (!sharedElements.hasValue()) sharedElements = defaultOptions.sharedElements
    }

    fun hasValue() = id.hasValue() || enabled.hasValue() || waitForRender.hasValue()

    fun getAnimation(view: View) = getAnimation(view, AnimatorSet())

    fun hasElementTransitions() = elementTransitions.hasValue() || sharedElements.hasValue()

    fun getAnimation(view: View, defaultAnimation: AnimatorSet): AnimatorSet {
        if (!hasAnimation()) return defaultAnimation
        return AnimatorSet().apply { playTogether(valueOptions.map { it.getAnimation(view) }) }
    }

    val duration: Int
        get() = CollectionUtils.reduce(valueOptions, 0, { item: ValueAnimationOptions, currentValue: Int -> max(item.duration[currentValue], currentValue) })

    open fun hasAnimation(): Boolean = valueOptions.isNotEmpty()

    fun isFadeAnimation(): Boolean = valueOptions.size == 1 && valueOptions.find(ValueAnimationOptions::isAlpha) != null

    fun setValueDy(animation: Property<View?, Float?>?, fromDelta: Float, toDelta: Float) {
        CollectionUtils.first(valueOptions, { o: ValueAnimationOptions -> o.equals(animation) }) { param: ValueAnimationOptions ->
            param.setFromDelta(fromDelta)
            param.setToDelta(toDelta)
        }
    }

    fun getAnimationValues() = ArrayList<ValueAnimationOptions>(valueOptions)
}