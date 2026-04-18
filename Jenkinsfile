pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out code...'
                git branch: 'main', url: 'https://github.com/Flowercatgirl/oksanapetitions.git'
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building...'
                sh 'ls -la'
                sh 'echo "Build completed"'
            }
        }
        
        stage('Manual Approval') {
            input {
                message 'Deploy to Tomcat?'
                ok 'Deploy'
            }
            steps {
                echo 'Approval received. Proceeding to deploy...'
            }
        }
        
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                sh 'echo "Deployment would happen here"'
            }
        }
    }
}
