pipeline {
    agent any

    environment {
        GITHUB_REPO = 'devops-jenkins'
        GITHUB_ACCOUNT = 'blazejwcislo'
        GITHUB_CREDENTIALS = 'github-token'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/master']],
                    userRemoteConfigs: [[
                        url: "https://github.com/${GITHUB_ACCOUNT}/${env.GITHUB_REPO}.git",
                        credentialsId: "${GITHUB_CREDENTIALS}"
                    ]]
                ])
                script {
                    def COMMIT_SHA = sh(script: 'git rev-parse HEAD', returnStdout: true).trim()
                    echo "Commit SHA: ${COMMIT_SHA}"

                    withEnv([
                        "GIT_URL=https://github.com/${GITHUB_ACCOUNT}/${env.GITHUB_REPO}.git",
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
