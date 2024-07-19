pipeline {
    agent any

    stages {
        stage('Checkout') {
           steps {
                          git(branch: 'main',url: 'https://github.com/ahmedsalah95/framekins.git', credentialsId: 'user-pass')
                 }
        }

        stage('Build and Test') {
            steps {
                script {
                    // Build the Docker image for the tests
                    docker.build('selenium-test-image', '-f Dockerfile.test .')
                    docker.image('selenium-test-image').inside('--link selenium-chrome:chrome -v test_results:/home/selenium/test_results') {
                        // Run the tests inside the Docker container
                        sh 'mvn clean test'
                    }
                }
            }
        }

        stage('Publish Reports') {
            steps {
                cucumber 'target/cucumber-reports/*.json'
            }
        }
    }

    triggers {
        githubPush()
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.html', allowEmptyArchive: true
        }
    }
}
