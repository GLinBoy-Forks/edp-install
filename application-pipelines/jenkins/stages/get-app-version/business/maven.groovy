/* Copyright 2018 EPAM Systems.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

See the License for the specific language governing permissions and
limitations under the License. */

def run(vars) {
    dir("${vars.workDir}") {
        vars['pomVersion'] = sh(
                script: """
                        mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version|grep -Ev '(^\\[|Download\\w+:)'
                    """,
                returnStdout: true
        ).trim().toLowerCase()
    }
    println("[JENKINS][DEBUG] Pom version - ${vars.pomVersion}")
    vars['businissAppVersion'] = "${vars.pomVersion}-${BUILD_NUMBER}"
}
return this;