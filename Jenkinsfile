pipeline {
    agent any
    tools{
        maven 'maven_3_8_1'
    }
    stages{
        stage('Build maven'){
            steps{
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/hugomarciel/payroll-frontend']])
                bat 'mvn clean package'
            }
        }

       // stage('Unit Tests') {
        //    steps {
         //       // Run Maven 'test' phase. It compiles the test sources and runs the unit tests
          //      bat 'mvn test' // Use 'bat' for Windows agents or 'sh' for Unix/Linux agents
           // }
        //}

        stage('Build docker image'){
            steps{
                script{
                    bat 'docker build --build-arg VITE_PAYROLL_BACKEND_SERVER=18.116.68.206 --build-arg VITE_PAYROLL_BACKEND_PORT=8090 -t hugomarciel/payroll-frontend:latest1 .'
                }
            }
        }
        stage('Push image to Docker Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
                        // Configurar las credenciales en Git para este pipeline
                        sh 'git config --global credential.helper store'
                        sh 'echo "https://${GITHUB_TOKEN}:x-oauth-basic@github.com" > ~/.git-credentials'

                        // Hacer push de la imagen a Docker Hub
                        bat 'docker push hugomarciel/payroll-frontend:latest'
                }
            }
        }
    }
}
