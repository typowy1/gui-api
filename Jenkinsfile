pipeline {
    agent any
    stages {
        stage('Build test code') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Execute tests') {
            steps {
            if(TestSuite.contains("gui") {
                bat "mvn clean test -Dbrowser=${params.browser} -Dsurefire.suiteXmlFiles=src/test/resources/testSuite/${params.TestSuite}.xml"
            } else {
                bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testSuite/${params.TestSuite}.xml"
            }
        }
    }
    post {
        always {
            script {
                allure([
                        includeProperties: false,
                        jdk              : '',
                        properties       : [],
                        reportBuildPolicy: 'ALWAYS',
                        results          : [[path: 'target/allure-results']]
                ])
            }
        }
        failure {
        emailext (
        to: "${params.emailRecipient}",
        subject: "Build failed in Jenkins: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
        body:
        "Allure Report: ${env.BUILD_URL}/allure <br> Console Output: ${env.BUILD_URL}/console",
        mimeType: 'text/html'
         );
        }
    }
}

