package org.utbot.cpp.clion.plugin.ui.utbotToolWindow

import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import org.utbot.cpp.clion.plugin.UTBot
import org.utbot.cpp.clion.plugin.ui.utbotToolWindow.logsToolWindow.ConsoleToolWindow
import org.utbot.cpp.clion.plugin.ui.utbotToolWindow.targetToolWindow.UTBotTargetsController

class UTBotToolWindowFactory : ToolWindowFactory, DumbAware {
    private val logger = Logger.getInstance(this::class.java)

    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        logger.info("createToolWindowContent was called")
        val contentManager = toolWindow.contentManager
        val targetsToolWindow = contentManager.factory.createContent(
            project.service<UTBotTargetsController>().targetsToolWindow, UTBot.message("toolwindow.targets.displayName"), false
        )
        val logsToolWindow =
            contentManager.factory.createContent(ConsoleToolWindow(project), UTBot.message("toolwindow.logs.displayName"), false)
        toolWindow.contentManager.addContent(logsToolWindow)
        toolWindow.contentManager.addContent(targetsToolWindow)
    }
}
