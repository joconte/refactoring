pipeline {
	 agent {
		label 'master'
	 }
	 triggers {
        pollSCM('H/1 * * * *')
    }
    options {
    disableConcurrentBuilds()
    buildDiscarder(logRotator(numToKeepStr: '30', daysToKeepStr: '90'))
    }
    stages {
        stage('Clean and checkout project') {
            steps{
                deleteDir()
                checkout(changelog: false, scm: scm)
            }
        }
        stage('Build') {
            steps{
                sh "mvn package"
            }
        }
        stage('Inspection') {
             steps{
                sh "mvn sonar:sonar"
             }
        }
    }
    post {
        always {
            step([$class: 'Mailer', recipients: [emailextrecipients([[$class:
            'CulpritsRecipientProvider'], [$class: 'RequesterRecipientProvider']])].join(' ')])
            archiveArtifacts 'target/*.jar'
            junit 'target/surefire-reports/*.xml'
        }
    }
}