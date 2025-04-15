pipeline {
    agent any
    tools {
        maven 'Maven 3.8.1'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Checkstyle') {
            steps {
                sh 'mvn checkstyle:check'
            }
        }
        stage('SpotBugs') {
            steps {
                sh 'mvn com.github.spotbugs:spotbugs-maven-plugin:check'
            }
        }
        stage('Dependency-Check') {
            steps {
                sh 'mvn org.owasp:dependency-check-maven:check'
            }
        }
    }
    post {
        success {
            script {
                githubCommitStatus(name: 'Jenkins Build', status: 'SUCCESS', context: 'build')
            }
        }
        failure {
            script {
                githubCommitStatus(name: 'Jenkins Build', status: 'FAILURE', context: 'build')
            }
        }
    }
}
