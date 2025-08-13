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
		            # Para a API antiga (ignora se não existir)
		            pkill -f /workspace/jenkins-0.0.1-SNAPSHOT.jar || true
		
		            # Aguarda 2 segundos para garantir que o processo foi morto
		            sleep 2
		
		            # Inicia a nova versão
		            nohup java -jar /workspace/jenkins-0.0.1-SNAPSHOT.jar > /workspace/api.log 2>&1 &
		        """
		    }
		}

    }
}
