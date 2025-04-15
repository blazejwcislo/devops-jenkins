pipeline {
    agent any

    tools {
        maven 'Maven 3.8.1'
    }

    environment {
        GITHUB_TOKEN = credentials('github-token')
    }

    stages {
        stage('Build') {
            steps {
                script {
                    githubNotify context: 'Build', description: 'Build started', status: 'PENDING'
                }
                sh 'mvn clean install'
            }
        }

        stage('Checkstyle') {
            steps {
                script {
                    githubNotify context: 'Checkstyle', description: 'Running Checkstyle', status: 'PENDING'
                }
                sh 'mvn checkstyle:check'
            }
        }

        stage('SpotBugs') {
            steps {
                script {
                    githubNotify context: 'SpotBugs', description: 'Running SpotBugs', status: 'PENDING'
                }
                sh 'mvn com.github.spotbugs:spotbugs-maven-plugin:check'
            }
        }

        stage('Dependency-Check') {
            steps {
                script {
                    githubNotify context: 'Dependency-Check', description: 'Running Dependency Check', status: 'PENDING'
                }
                sh 'mvn org.owasp:dependency-check-maven:check'
            }
        }
    }

    post {
        success {
            script {
                githubNotify context: 'Build', status: 'SUCCESS', description: 'Build passed'
                githubNotify context: 'Checkstyle', status: 'SUCCESS', description: 'No Checkstyle issues'
                githubNotify context: 'SpotBugs', status: 'SUCCESS', description: 'No SpotBugs issues'
                githubNotify context: 'Dependency-Check', status: 'SUCCESS', description: 'No vulnerabilities'
            }
        }

        failure {
            script {
                githubNotify context: 'Build', status: 'FAILURE', description: 'Build failed'
                githubNotify context: 'Checkstyle', status: 'FAILURE', description: 'Checkstyle failed'
                githubNotify context: 'SpotBugs', status: 'FAILURE', description: 'SpotBugs failed'
                githubNotify context: 'Dependency-Check', status: 'FAILURE', description: 'Vulnerabilities found'
            }
        }
    }
}