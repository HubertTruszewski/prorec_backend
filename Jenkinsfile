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

        stage("build & SonarQube analysis") {
            agent any
            steps {
              withSonarQubeEnv('sonarqube') {
                sh 'mvn clean package sonar:sonar -Dsonar.projectKey=$sonar_project_key'
              }
            }
          }
          stage("Quality Gate") {
            steps {
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
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
    }
    post {
        always {
            discordSend description: "result:" + currentBuild.currentResult, link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME + " #" + env.BUILD_NUMBER, webhookURL: "https://discord.com/api/webhooks/1039587559984074802/g_jhnKHeB4LceQVX_Om2fmzlcwWgsOdDLq_Orz-q1TrySyvaYxFERBIYrnRK9QOO60xe", enableArtifactsList: true, showChangeset: true
        }
    }
}