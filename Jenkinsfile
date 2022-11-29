pipeline {
    agent any;
    tools {
    	jdk "graalvm"
        maven "maven"
    }

    environment {
        sonar_project_key = credentials('sonarqube_backend_project_key')
        postgres_credentials = credentials('postgres_server');
        pom = readMavenPom file: 'pom.xml'
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
                sh """
                    mvn clean package sonar:sonar -Dsonar.projectKey=$sonar_project_key -DdbName=prorec_db -DdbHost=${env.postgres_address} -DdbPort=${env.postgres_port} -DdbUser=${postgres_credentials_usr} -DdbPass=${postgres_credentials_psw}
                """
                updateGitlabCommitStatus name: 'build', state: 'success'
              }
            }
          }
         /* stage("Quality Gate") {
            steps {
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
              }
            }
          }*/

        stage("Test") {
            steps {
                gitlabCommitStatus(name: "test") {
                    sh """
                        mvn test -DdbName=prorec_db -DdbHost=${env.postgres_address} -DdbPort=${env.postgres_port} -DdbUser=${postgres_credentials_usr} -DdbPass=${postgres_credentials_psw}
                    """
                    updateGitlabCommitStatus name: 'test', state: 'success'
                    jacoco()
                }
            }
        }

        stage("Archive artifacts") {
            steps {
                archiveArtifacts artifacts: 'target/*.war', followSymlinks: false
            }
        }
        stage("Upload artifacts") {
            steps {
                nexusArtifactUploader artifacts: [[artifactId: pom.artifactId, classifier: '', file: 'target/prorec-0.0.1-SNAPSHOT.war', type: 'war']], credentialsId: 'b060b7af-7618-46c8-b951-04a189d155a4', groupId: pom.groupId, nexusUrl: '192.168.162.225:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'prorec-snapshots', version: pom.version
            }
        }
    }

    post {
        always {
            discordSend description: "result:" + currentBuild.currentResult, link: env.BUILD_URL, result: currentBuild.currentResult, title: JOB_NAME + " #" + env.BUILD_NUMBER, webhookURL: "https://discord.com/api/webhooks/1039587559984074802/g_jhnKHeB4LceQVX_Om2fmzlcwWgsOdDLq_Orz-q1TrySyvaYxFERBIYrnRK9QOO60xe", enableArtifactsList: true, showChangeset: true
        }
    }
}
