pipeline {
    agent any

    triggers {
            pollSCM('* * * * *')
    }

    environment {
        APPLICATION_NAME = 'visitormanagmentapp'
    }

    stages {
        stage('Build') {
            steps {
                sh './mvnw clean install'
            }
        }
    }
}
