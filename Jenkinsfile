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
                sh 'mvn clean verify'
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
    }
}

