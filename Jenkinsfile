pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java17'
    }

    environment {
        // Nome do jar gerado pelo Maven (ajuste se necessário)
        JAR_NAME = "jenkins-0.0.1-SNAPSHOT.jar"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/WillyanAnjos/jenkins-pipeline.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Run API Locally') {
            steps {
                // Para rodar em background e logar em arquivo
                sh "nohup java -jar target/$JAR_NAME > api.log 2>&1 &"
            }
        }
    }
}
