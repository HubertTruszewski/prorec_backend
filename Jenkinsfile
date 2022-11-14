pipeline {
    agent any;
    tools {
        maven "maven"
    }

    environment {
        sonar_project_key = credentials('sonarqube_backend_project_key')
    }

    options {
        gitLabConnection(gitLabConnection: 'gitlab_backend_connection')
        gitlabBuilds(builds: ['build', 'test'])
        timestamps()
    }

    stages {
        stage("Build") {
            steps {
                gitlabCommitStatus(name: "build") {
                    sh 'mvn package'
                }
            }
        }
        stage("Test") {
            steps {
                gitlabCommitStatus(name: "test") {
                    sh 'mvn test'
                    updateGitlabCommitStatus name: 'test', state: 'success'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                def mvn = tool 'maven';
                withSonarQubeEnv() {
                  sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=$sonar_project_key"
                }
            }
        }
    }
    post {
        always {
            discordSend description: "result:" + currentBuild.currentResult, link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME + " #" + env.BUILD_NUMBER, webhookURL: "https://discord.com/api/webhooks/1039587559984074802/g_jhnKHeB4LceQVX_Om2fmzlcwWgsOdDLq_Orz-q1TrySyvaYxFERBIYrnRK9QOO60xe", enableArtifactsList: true, showChangeset: true
        }
    }
}