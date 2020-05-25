pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
    post {
        always {
            junit 'build/reports/**/*.xml'
        }
    }
}