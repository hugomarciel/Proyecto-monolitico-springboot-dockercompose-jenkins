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
                        // Cambia la URL de tu repositorio para incluir el token
                        def repoUrl = "https://%GITHUB_TOKEN%@github.com/hugomarciel/Proyecto-monolitico.git"
                        checkout([$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: repoUrl]]])
                }
            }
        }
    }
}