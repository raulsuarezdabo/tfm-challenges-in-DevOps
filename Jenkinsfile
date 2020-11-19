// Initialize a LinkedHashMap / object to share between stages
def pipelineContext = [:]

pipeline {
    agent any
    environment {
        DOCKER_REPOSITORY = "raulsuarezdabo/tfm-devsecop-jenkins"
        RC_BRANCH = 'develop'
        RELEASE_BRANCH = 'main'
    }
    stages {
        stage('Dependencies') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Dependency stage'
                script {
                    sh "echo 'Downloading dependencies...'"
                    sh "mvn install -DskipTests=true"
                    sh "echo 'Verifying dependencies...'"
                    sh "mvn dependency-check:check -Dformat=xml"
                    dependencyCheckPublisher pattern: 'dependency-check-report.xml'
                }
            }
        }
        stage('Testing') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                echo 'Test stage'
                script {
                    sh "echo 'JUnit testing...'"
                    sh "mvn test"
                    sh "echo 'Integration testing...'"
                    sh "mvn test -Dtest=IntegrationTest"
                    jacoco(execPattern: 'target/jacoco.exec')
                }
            }
        }
        stage('Publish Release') {
            when {
                anyOf {
                    branch RC_BRANCH
                    branch RELEASE_BRANCH
                }
            }
            steps{
                script {
                    if (env.BRANCH_NAME == RC_BRANCH) {
                        MSG_CASE = 'Publishing Release Candidate'
                        CONTAINER_VERSION = env.BUILD_NUMBER+'-RC'
                    } else if (env.BRANCH_NAME == RELEASE_BRANCH) {
                        MSG_CASE = 'Publishing Release'
                        CONTAINER_VERSION = env.BUILD_NUMBER
                    } else {
                        error "Pipeline error, impossible to create an image on branch ${env.BRANCH_NAME}"
                    }
                    echo MSG_CASE
                    dockerImage = docker.build(DOCKER_REPOSITORY)
                    docker.withRegistry("", "docker_hub_login") {
                        dockerImage.push("${CONTAINER_VERSION}")
                    }
                    echo "Container ${DOCKER_REPOSITORY}:${CONTAINER_VERSION} pushed: OK"
                }
            }
        }
        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo 'Deploying...not implemented yet'
                
            }
        }
    }
}

