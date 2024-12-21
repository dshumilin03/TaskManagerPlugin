package com.example.taskmanagerplugin

import com.intellij.openapi.ui.SimpleToolWindowPanel
import javax.swing.*

class TaskManagerWindow : SimpleToolWindowPanel(true, true) {
    private val taskListModel: DefaultListModel<String> = DefaultListModel()
    private val taskList: JList<String> = JList(taskListModel)

    init {
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

        // Вставляем панель в Tool Window
        setContent(mainPanel)
    }
}
