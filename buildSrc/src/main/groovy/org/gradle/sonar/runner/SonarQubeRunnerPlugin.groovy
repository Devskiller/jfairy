package org.gradle.sonar.runner

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.sonar.runner.SonarRunnerPlugin

class SonarQubeRunnerPlugin implements Plugin<Project> {

    void apply(Project project) {
		SonarRunnerPlugin oldPlugin = new SonarRunnerPlugin()
		oldPlugin.apply(project)

        def sonarQubeRunnerTask = project.tasks.create("sonarQubeRunner", SonarQubeRunner)
	    sonarQubeRunnerTask.conventionMapping.with {
		    sonarProperties = { project.tasks.sonarRunner.sonarProperties }
	    }
        sonarQubeRunnerTask.dependsOn { project.tasks.sonarRunner.getDependsOn() }
    }
}
