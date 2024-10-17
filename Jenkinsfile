pipeline {
    agent any
    tools{
        maven 'maven_3_8_1'
    }
    stages{
        stage('Build maven'){
            steps{
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/hugomarciel/Proyecto-monolitico']])
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
                    bat 'docker build --no-cache -t hugomarciel/payroll-backend:latest .'
                }
            }
        }
        stage('Push image to Docker Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dhpswid', variable: 'dhpsw')]) {
                        bat 'docker login -u hugomarciel -p %dhpsw%'
                   }
                   bat 'docker push hugomarciel/payroll-backend:latest'
                }
            }
        }
    }
}
