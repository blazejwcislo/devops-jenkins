pipeline {
    agent any
    tools {
        maven 'Maven 3.8.1'
    }
    environment {
        GITHUB_ACCOUNT = 'blazejwcislo'
        GITHUB_REPO = 'devops-jenkins' 
        GITHUB_CREDENTIALS = 'github-token'
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

        stage('REPORT') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/master']],
                    userRemoteConfigs: [[
                        url: "https://github.com/${env.GITHUB_ACCOUNT}/${env.GITHUB_REPO}.git",
                        credentialsId: "${GITHUB_CREDENTIALS}"
                    ]]
                ])
                script {
                    def COMMIT_SHA = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
                    echo "Commit SHA: ${COMMIT_SHA}"

                    withEnv([
                        "GIT_URL=https://github.com/${env.GITHUB_ACCOUNT}/${env.GITHUB_REPO}.git",
                        "GIT_COMMIT=${COMMIT_SHA}"
                    ]) {
                        githubNotify context: 'CI/Jenkins',
                                     description: 'Build succeeded',
                                     status: 'SUCCESS',
                                     repo: "${env.GITHUB_REPO}",
                                     account: "${env.GITHUB_ACCOUNT}",
                                     sha: "${COMMIT_SHA}",
                                     credentialsId: "${GITHUB_CREDENTIALS}"
                    }
                }
            }
        }
    }
}
