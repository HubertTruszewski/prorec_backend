pipeline {
    agent any;
    tools {
        maven "maven"
    }
    stages {
        stage("Git checkout") {
            steps {
                git branch: '${BRANCH_NAME}', credentialsId: 'd43bc922-d44e-47af-be91-0ac61fa551c4', url: 'https://gitlab-stud.elka.pw.edu.pl/prorec/backend.git'
            }
        }
        stage("Build") {
            steps {
                sh 'mvn package'
            }
        }
        stage("Test") {
            steps {
                sh 'mvn test'
            }
        }
    }
}