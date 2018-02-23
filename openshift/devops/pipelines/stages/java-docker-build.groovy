def run(vars) {
    script {
        openshift.withCluster() {
            openshift.withProject() {
                if (!openshift.selector("buildconfig", "${vars.gerritProject}").exists())
                    openshift.newBuild("--name=${vars.gerritProject}", "--image-stream=s2i-java", "--binary=true")
                openshift.selector("bc", "${vars.gerritProject}").startBuild("--from-dir=${vars.workDir}/target", "--wait=true")
                openshift.tag("${vars.dockerImageProject}/${vars.gerritProject}:latest","${vars.dockerImageProject}/${vars.gerritProject}:snapshot")
            }
        }
    }
}

return this;