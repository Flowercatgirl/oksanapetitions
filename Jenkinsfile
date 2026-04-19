pipeline {
    agent any

    triggers {
        githubPush()
    }

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
                sh 'cd /var/lib/jenkins/oksanapetitions && mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'cd /var/lib/jenkins/oksanapetitions && mvn test'
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging as WAR...'
                sh 'cd /var/lib/jenkins/oksanapetitions && mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/*.war', fingerprint: true
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
                echo 'Stopping Tomcat to free memory...'
                sh 'sudo systemctl stop tomcat10 || sudo systemctl stop tomcat || true'
                sleep(time: 5, unit: 'SECONDS')
                echo 'Deploying WAR to Tomcat webapps...'
                sh 'sudo cp /var/lib/jenkins/oksanapetitions/target/*.war /opt/tomcat10/webapps/ROOT.war'
                echo 'Starting Tomcat...'
                sh 'sudo systemctl start tomcat10 || sudo systemctl start tomcat || true'
                echo 'Deployment complete!'
            }
        }
    }

    post {
        failure {
            echo 'Pipeline failed. Ensuring Tomcat is running...'
            sh 'sudo systemctl start tomcat10 || sudo systemctl start tomcat || true'
        }
    }
}
