pipeline {
    agent any

    environment {
        IMAGE_TAG = "leave-service:v1.0"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/2024sl70016-web/leave-service-2024sl70016'
            }
        }
        stage('Build and Test') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('SonarQube Code Inspection') {
            steps {
                withSonarQubeEnv('SonarQube-Server') {
                    sh '''
                        mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                          -Dsonar.projectKey=leave-service-2024sl70016 \
                          -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                    '''
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${IMAGE_TAG} ."
            }
        }
        stage('Deploy to Staging') {
            steps {
                sh """
                    docker stop leave-service-staging || true
                    docker rm leave-service-staging || true
                    docker run -d \
                      --name leave-service-staging \
                      -e APP_ENV=staging \
                      -p 8085:8080 \
                      ${IMAGE_TAG}
                """
            }
        }
        stage('Approval') {
            steps {
                input message: 'Staging deployment verified. Approve deployment to Production?', ok: 'Deploy to Production'
            }
        }
        stage('Deploy to Production') {
            steps {
                sh """
                    docker stop leave-service-production || true
                    docker rm leave-service-production || true
                    docker run -d \
                      --name leave-service-production \
                      -e APP_ENV=production \
                      -p 8086:8080 \
                      ${IMAGE_TAG}
                """
            }
        }
    }
}

