pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                githubNotify context: 'CI/Jenkins', description: 'Checkout started', status: 'PENDING'
                checkout scm
                githubNotify context: 'CI/Jenkins', description: 'Checkout completed', status: 'SUCCESS'
            }
        }

        stage('Build') {
            steps {
                githubNotify context: 'CI/Jenkins', description: 'Build started', status: 'PENDING'
                sh 'echo "Building..."'
            }
        }
    }

    post {
        success {
            githubNotify context: 'CI/Jenkins', description: 'Build succeeded', status: 'SUCCESS'
        }
        failure {
            githubNotify context: 'CI/Jenkins', description: 'Build failed', status: 'FAILURE'
        }
    }
}