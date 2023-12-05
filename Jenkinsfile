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
                bat "mvn clean test -Dbrowser=${params.browser} -Dsurefire.suiteXmlFiles=src/test/resources/testSuite/${params.TestSuite}.xml"
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

            mail bcc: '', body:"<>Example</b><br> Build URL: FAILURE: ${env.BUILD_URL} <br> Console Output: ${env.BUILD_URL}/console" cc: '', charset:'UTF-8', from'', mimeType: 'text/html, subject: Build failed in Jenkins: ${env.JOB_NAME} #${env.BUILD_NUMBER}", to: "${params.emailRecipient}";
        }
    }
}

