package com.morcinek.classgraph.plugin;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class ShowClassGraph extends AnAction {

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        String txt = Messages.showInputDialog(project, "What is your name?", "Input your name", Messages.getQuestionIcon());
        Messages.showMessageDialog(project, "Hello, " + txt + "!\n I am glad to see you.", "Information", Messages.getInformationIcon());
    }

    @Override
    public void update(AnActionEvent event) {
        event.getPresentation().setVisible(shouldBeVisible(event));
   }

    private boolean shouldBeVisible(AnActionEvent event) {
        VirtualFile projectFile = event.getData(PlatformDataKeys.PROJECT_FILE_DIRECTORY);
        VirtualFile[] files  = event.getData(PlatformDataKeys.VIRTUAL_FILE_ARRAY);
        if (files != null && files.length == 1) {
            VirtualFile file = files[0];
            return projectFile != null && projectFile.equals(file);
        } else {
            return false;
        }
    }
}
