package com.example.taskmanagerplugin

import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.openapi.project.Project
import javax.swing.*

class TaskManagerToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        // Создайте и добавьте панель для ToolWindow
        val taskListModel: DefaultListModel<String> = DefaultListModel()
        val taskList: JList<String> = JList(taskListModel)

        // Панель с кнопками
        val buttonPanel = JPanel()
        val addButton = JButton("Add Task")
        val removeButton = JButton("Remove Task")
        val editButton = JButton("Edit Task")

        buttonPanel.add(addButton)
        buttonPanel.add(removeButton)
        buttonPanel.add(editButton)

        // Добавляем действия для кнопок
        addButton.addActionListener {
            val newTask = JOptionPane.showInputDialog("Enter a new task:")
            if (!newTask.isNullOrEmpty()) {
                taskListModel.addElement(newTask)
            }
        }

        removeButton.addActionListener {
            val selectedTask = taskList.selectedValue
            if (selectedTask != null) {
                taskListModel.removeElement(selectedTask)
            }
        }

        editButton.addActionListener {
            val selectedTask = taskList.selectedValue
            if (selectedTask != null) {
                val editedTask = JOptionPane.showInputDialog("Edit task:", selectedTask)
                if (!editedTask.isNullOrEmpty()) {
                    taskListModel.set(taskList.selectedIndex, editedTask)
                }
            }
        }

        // Основная компоновка
        val mainPanel = JPanel()
        mainPanel.layout = BoxLayout(mainPanel, BoxLayout.Y_AXIS)
        mainPanel.add(JScrollPane(taskList))
        mainPanel.add(buttonPanel)

        // Добавляем контент Tool Window
        toolWindow.contentManager.addContent(toolWindow.contentManager.factory.createContent(mainPanel, "", false))
    }
}
