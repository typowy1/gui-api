pipeline {
    agent any
    stages {
        stage('Build test code') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }
        stage('Execute test') {
            steps {
                script {
                    catchError (buildResult: 'FAILURE', stageResult: 'FAILURE') {
                        bat "mvn clean test -Dbrowser=${params.Browser} -Dsurefire.suiteXmlFiles=src/test/resources/testSuite/${params['Test Suite']}.xml"
                    }
                }
            }
        }
        stage('Generate allure report') {
            steps {
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
        }
    }
}
