pipeline {
	agent any
	
	tools {
		maven 'maven-tool'
	}
	
	stages {
		stage('Build') {
		    steps {
		    	sh 'mvn clean install';
		    }
		}
		
		stage('deploy') {
			steps {
				sh "ssh-agent bash -c 'ssh-add /tmp/id_rsa; git push ssh://588ac97789f5cff8a700001b@auth-devok.rhcloud.com/~/git/auth.git/ HEAD:master'";
//				sshagent(credentials: ['jenkins-docker']) {
					
//				}
			}
		}
	}
}