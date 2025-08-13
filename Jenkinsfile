pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java21'
    }

    environment {
        IMAGE_NAME = "api-financeiro"
        IMAGE_TAG = "latest"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'git@github.com:seu-usuario/seu-repo-privado.git',
                    credentialsId: 'github-ssh'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t $IMAGE_NAME:$IMAGE_TAG ."
            }
        }

        stage('Deploy API') {
            steps {
                sh "docker stop api-financeiro || true && docker rm api-financeiro || true"
                sh "docker run -d --name api-financeiro -p 8081:8080 $IMAGE_NAME:$IMAGE_TAG"
            }
        }
    }
}
