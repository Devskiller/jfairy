/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.sonar.runner

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logger
import org.gradle.api.tasks.TaskAction
import org.sonar.runner.api.ForkedRunner

import static org.gradle.api.logging.Logging.getLogger

class SonarQubeRunner extends DefaultTask {
    private static final Logger log = getLogger(SonarQubeRunner)

    Properties sonarProperties

    @TaskAction
    void run() {
        def properties = getSonarProperties()
        if (log.infoEnabled) {
            log.info("Executing SonarQube Runner with properties:\n{}", properties.sort().collect { key, value -> "$key: $value" }.join("\n"))
        }
        def runner = ForkedRunner.create()
	    runner.addProperties(properties)
        runner.execute()
    }
}
