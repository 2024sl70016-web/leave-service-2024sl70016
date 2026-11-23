pipeline {
    agent any

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
                sh 'docker build -t leave-service:staging .'
            }
        }
        stage('Deploy to Staging') {
            steps {
                sh '''
                    docker stop leave-service-staging || true
                    docker rm leave-service-staging || true
                    docker run -d \
                      --name leave-service-staging \
                      -e APP_ENV=staging \
                      -p 8085:8080 \
                      leave-service:staging
                '''
            }
        }
    }
}

