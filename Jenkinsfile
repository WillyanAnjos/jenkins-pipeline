pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java17'
    }

    environment {
        JAR_NAME = "jenkins-0.0.1-SNAPSHOT.jar"  // Ajuste para seu jar
        WORKSPACE_HOST = "/workspace"           // Caminho compartilhado com host
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

        stage('Copy Jar to Host') {
            steps {
                // Copia o jar para o diretório compartilhado com o host
                sh "cp target/$JAR_NAME $WORKSPACE_HOST/"
            }
        }

        stage('Restart API on Host') {
            steps {
                // Mata a API antiga, se estiver rodando, e inicia a nova
                sh """
                    pkill -f $WORKSPACE_HOST/$JAR_NAME || true
                    nohup java -jar $WORKSPACE_HOST/$JAR_NAME > $WORKSPACE_HOST/api.log 2>&1 &
                """
            }
        }
    }
}
