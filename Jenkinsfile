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
                sh """
                    echo "Matando processo antigo (se existir)..."
                    pkill -f /workspace/$JAR_NAME || true

                    echo "Aguardando 5 segundos para garantir que o processo antigo terminou..."
                    sleep 5

                    echo "Iniciando nova versão da API..."
                    nohup java -jar /workspace/$JAR_NAME > /workspace/api.log 2>&1 &
                    disown

                    echo "API reiniciada com sucesso!"
                """
            }
        }
    }

    post {
        success {
            echo "Pipeline finalizado com sucesso!"
        }
        failure {
            echo "Pipeline falhou!"
        }
    }
}
