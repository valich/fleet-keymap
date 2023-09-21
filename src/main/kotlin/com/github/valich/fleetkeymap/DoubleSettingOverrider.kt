package com.github.valich.fleetkeymap

import com.intellij.ide.actions.SearchEverywhereAction
import com.intellij.ide.actions.runAnything.RunAnythingAction
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.AnActionResult
import com.intellij.openapi.actionSystem.ex.AnActionListener
import com.intellij.openapi.options.advanced.AdvancedSettings

private const val suppressSEKeySettingId = "ide.suppress.double.click.handler"

//private const val myId = "com.github.valich.FleetKeymap"

class DoubleSettingOverrider : AnActionListener {
    private var settingValue: Boolean = false

    override fun beforeActionPerformed(action: AnAction, event: AnActionEvent) {
        if (action is SearchEverywhereAction || action is RunAnythingAction) {
            settingValue = AdvancedSettings.getBoolean(suppressSEKeySettingId)
            AdvancedSettings.setBoolean(suppressSEKeySettingId, true)
        }
    }

    override fun afterActionPerformed(action: AnAction, event: AnActionEvent, result: AnActionResult) {
        if (action is SearchEverywhereAction || action is RunAnythingAction) {
            AdvancedSettings.setBoolean(suppressSEKeySettingId, settingValue)
        }
    }
}

//class DoubleSettingOverrider : DynamicPluginListener {
//    override fun pluginLoaded(pluginDescriptor: IdeaPluginDescriptor) {
//        if (pluginDescriptor.pluginId.idString == myId) {
//            AdvancedSettings.setBoolean(suppressSEKeySettingId, true)
//        }
//    }
//
//    override fun beforePluginUnload(pluginDescriptor: IdeaPluginDescriptor, isUpdate: Boolean) {
//        if (pluginDescriptor.pluginId.idString == myId) {
//            AdvancedSettings.setBoolean(suppressSEKeySettingId, false)
//        }
//    }
//}