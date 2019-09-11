pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'docker build -t agenda . '
      }
    }
    stage('stop') {
      steps {
        sh 'docker stop agenda || true && docker rm agenda || true'
      }
    }
    stage('run') {
      steps {
        sh 'docker run -d -p 8080:8080 --name agenda agenda'
      }
    }
  }
}