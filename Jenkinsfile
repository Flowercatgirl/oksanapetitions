pipeline {
    agent any
    
    tools {
        maven 'Maven-3'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Flowercatgirl/oksanapetitions.git'
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
                sh 'cp target/*.war oksanapetitions.war'
            }
        }
        
        stage('Manual Approval') {
            input {
                message "Deploy to Tomcat?"
                ok "Deploy"
            }
        }
        
        stage('Deploy') {
            steps {
                sh 'curl -v -u admin:admin123 -T oksanapetitions.war "http://localhost:9090/manager/text/deploy?path=/&update=true"'
            }
        }
    }
}
