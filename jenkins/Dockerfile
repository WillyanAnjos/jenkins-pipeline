FROM jenkins/jenkins:lts

USER root

# Instalar Maven e Docker CLI
RUN apt-get update && apt-get install -y maven docker.io && rm -rf /var/lib/apt/lists/*

# Plugins essenciais
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

USER jenkins
