package com.morcinek.classgraph.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.morcinek.uml.graph.SimulationVisualization;
import com.morcinek.uml.relations.RelationsProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2014 Tomasz Morcinek. All rights reserved.
 */
public class ShowClassGraph extends AnAction {

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        String path = project.getBasePath();
        RelationsProvider relationsProvider = new RelationsProvider();
        final Map<String, HashMap<String, Integer>> relations = relationsProvider.provideRelations(path, null);
        SimulationVisualization simulationVisualization = new SimulationVisualization(relations);
        simulationVisualization.setVisible(true);
    }

    @Override
    public void update(AnActionEvent event) {
        event.getPresentation().setVisible(shouldBeVisible(event));
    }

    private boolean shouldBeVisible(AnActionEvent event) {
        VirtualFile projectFile = event.getData(PlatformDataKeys.PROJECT_FILE_DIRECTORY);
        VirtualFile[] files = event.getData(PlatformDataKeys.VIRTUAL_FILE_ARRAY);
        if (files != null && files.length == 1) {
            VirtualFile file = files[0];
            return projectFile != null && projectFile.equals(file);
        } else {
            return false;
        }
    }
}
