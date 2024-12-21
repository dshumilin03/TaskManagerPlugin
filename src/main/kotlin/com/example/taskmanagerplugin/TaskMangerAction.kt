package com.example.taskmanagerplugin

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowManager

class TaskManagerAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project: Project = e.project ?: return
        val toolWindowManager: ToolWindowManager = ToolWindowManager.getInstance(project)
        val toolWindow: ToolWindow = toolWindowManager.getToolWindow("TaskManager") ?: return

        // Показываем Tool Window
        toolWindow.show {
            // Здесь мы можем добавить контент в Tool Window, если нужно
        }
    }
}
