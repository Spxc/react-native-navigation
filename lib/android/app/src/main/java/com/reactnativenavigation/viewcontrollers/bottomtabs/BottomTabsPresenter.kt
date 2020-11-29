package com.reactnativenavigation.viewcontrollers.bottomtabs

import android.animation.Animator
import android.graphics.Color
import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.core.view.forEachIndexed
import androidx.core.view.updateMargins
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation.TitleState
import com.reactnativenavigation.options.Options
import com.reactnativenavigation.viewcontrollers.viewcontroller.ViewController
import com.reactnativenavigation.views.bottomtabs.BottomTabs

class BottomTabsPresenter(private val tabs: List<ViewController<*>>, private var defaultOptions: Options) {
    private val bottomTabFinder: BottomTabFinder = BottomTabFinder(tabs)
    private lateinit var bottomTabs: BottomTabs
    private lateinit var tabSelector: TabSelector
    lateinit var tabsAnimator: BottomTabsAnimator
        private set
    private val defaultTitleState: TitleState
        get() {
            bottomTabs.forEachIndexed { index, _ ->
                if (bottomTabs.getItem(index).hasIcon()) return TitleState.SHOW_WHEN_ACTIVE
            }
            return TitleState.ALWAYS_SHOW
        }

    fun setDefaultOptions(defaultOptions: Options) {
        this.defaultOptions = defaultOptions
    }

    fun bindView(bottomTabs: BottomTabs, tabSelector: TabSelector, animator: BottomTabsAnimator) {
        this.bottomTabs = bottomTabs
        this.tabSelector = tabSelector
        tabsAnimator = animator
    }

    fun mergeOptions(options: Options, view: ViewController<*>) {
        mergeBottomTabsOptions(options, view)
    }

    fun applyOptions(options: Options) {
        applyBottomTabsOptions(options.copy().withDefaultOptions(defaultOptions))
    }

    fun applyChildOptions(options: Options, child: ViewController<*>) {
        val tabIndex = bottomTabFinder.findByControllerId(child.id)
        if (tabIndex >= 0) {
            applyBottomTabsOptions(options.copy().withDefaultOptions(defaultOptions))
            applyDrawBehind(tabIndex)
        }
    }

    fun mergeChildOptions(options: Options, child: ViewController<*>) {
        mergeBottomTabsOptions(options, child)
        val tabIndex = bottomTabFinder.findByControllerId(child.id)
        if (tabIndex >= 0) mergeDrawBehind(tabIndex)
    }

    private fun mergeBottomTabsOptions(options: Options, view: ViewController<*>) {
        val bottomTabsOptions = options.bottomTabsOptions
//        val animations = options.animations
        if (options.layout.direction.hasValue()) bottomTabs.setLayoutDirection(options.layout.direction)
        if (bottomTabsOptions.preferLargeIcons.hasValue()) bottomTabs.setPreferLargeIcons(bottomTabsOptions.preferLargeIcons.get())
        if (bottomTabsOptions.titleDisplayMode.hasValue()) {
            bottomTabs.titleState = bottomTabsOptions.titleDisplayMode.toState()
        }
        if (bottomTabsOptions.backgroundColor.hasValue()) {
            bottomTabs.setBackgroundColor(bottomTabsOptions.backgroundColor.get())
        }
        if (bottomTabsOptions.currentTabIndex.hasValue()) {
            val tabIndex = bottomTabsOptions.currentTabIndex.get()
            if (tabIndex >= 0) tabSelector.selectTab(tabIndex)
        }
        if (bottomTabsOptions.testId.hasValue()) {
            bottomTabs.tag = bottomTabsOptions.testId.get()
        }
        if (bottomTabsOptions.currentTabId.hasValue()) {
            val tabIndex = bottomTabFinder.findByControllerId(bottomTabsOptions.currentTabId.get())
            if (tabIndex >= 0) tabSelector.selectTab(tabIndex)
        }
        if (bottomTabsOptions.hideOnScroll.hasValue()) {
            bottomTabs.isBehaviorTranslationEnabled = bottomTabsOptions.hideOnScroll.get()
        }
        if (view.isViewShown) {
            if (bottomTabsOptions.visible.isTrue) {
                if (bottomTabsOptions.animate.isTrueOrUndefined) {
//                    animator.show(new AnimationOptions(), 0);
                } else {
                    bottomTabs.restoreBottomNavigation(false)
                }
            }
            if (bottomTabsOptions.visible.isFalse) {
                if (bottomTabsOptions.animate.isTrueOrUndefined) {
//                    animator.hide(new AnimationOptions(), 0, 0, null);
                } else {
                    bottomTabs.hideBottomNavigation(false)
                }
            }
        }
    }

    private fun applyDrawBehind(@IntRange(from = 0) tabIndex: Int) {
        tabs[tabIndex].applyBottomInset()
    }

    private fun mergeDrawBehind(tabIndex: Int) {
        tabs[tabIndex].applyBottomInset()
    }

    private fun applyBottomTabsOptions(options: Options) {
        val bottomTabsOptions = options.bottomTabsOptions
//        val animationsOptions = options.animations
        bottomTabs.setLayoutDirection(options.layout.direction)
        bottomTabs.setPreferLargeIcons(options.bottomTabsOptions.preferLargeIcons[false])
        bottomTabs.titleState = bottomTabsOptions.titleDisplayMode[defaultTitleState]
        bottomTabs.setBackgroundColor(bottomTabsOptions.backgroundColor[Color.WHITE])
        if (bottomTabsOptions.currentTabIndex.hasValue()) {
            val tabIndex = bottomTabsOptions.currentTabIndex.get()
            if (tabIndex >= 0) {
                bottomTabsOptions.currentTabIndex.consume()
                tabSelector.selectTab(tabIndex)
            }
        }
        if (bottomTabsOptions.testId.hasValue()) bottomTabs.tag = bottomTabsOptions.testId.get()
        if (bottomTabsOptions.currentTabId.hasValue()) {
            val tabIndex = bottomTabFinder.findByControllerId(bottomTabsOptions.currentTabId.get())
            if (tabIndex >= 0) {
                bottomTabsOptions.currentTabId.consume()
                tabSelector.selectTab(tabIndex)
            }
        }
        if (bottomTabsOptions.visible.isTrueOrUndefined) {
            if (bottomTabsOptions.animate.isTrueOrUndefined) {
//                animator.show(animationsOptions);
            } else {
                bottomTabs.restoreBottomNavigation(false)
            }
        }
        if (bottomTabsOptions.visible.isFalse) {
            if (bottomTabsOptions.animate.isTrueOrUndefined) {
//                animator.hide(animationsOptions);
            } else {
                bottomTabs.hideBottomNavigation(false)
            }
        }
        if (bottomTabsOptions.elevation.hasValue()) {
            bottomTabs.setUseElevation(true, bottomTabsOptions.elevation.get().toFloat())
        }
        bottomTabs.isBehaviorTranslationEnabled = bottomTabsOptions.hideOnScroll[false]
    }

    fun applyBottomInset(bottomInset: Int) {
        (bottomTabs.layoutParams as ViewGroup.MarginLayoutParams).updateMargins(bottom = bottomInset)
        bottomTabs.requestLayout()
    }

    fun getBottomInset(resolvedOptions: Options): Int {
        return if (resolvedOptions.withDefaultOptions(defaultOptions).bottomTabsOptions.isHiddenOrDrawBehind) 0 else bottomTabs.height
    }

    fun getPushAnimation(appearingOptions: Options): Animator {
        return tabsAnimator.getPushAnimation(
                appearingOptions.animations.push.bottomTabs,
                appearingOptions.bottomTabsOptions.visible
        )
    }

    fun getPopAnimation(appearingOptions: Options, disappearingOptions: Options): Animator {
        return tabsAnimator.getPopAnimation(
                disappearingOptions.animations.pop.bottomTabs,
                appearingOptions.bottomTabsOptions.visible
        )
    }

    fun getSetStackRootAnimation(appearingOptions: Options): Animator {
        return tabsAnimator.getSetStackRootAnimation(
                appearingOptions.animations.setStackRoot.bottomTabs,
                appearingOptions.bottomTabsOptions.visible
        )
    }
}