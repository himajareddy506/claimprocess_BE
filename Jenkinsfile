node('master')
{
stage('git checkout')
{
git branch:'dev',url:'https://github.com/himajareddy506/claimprocess_BE.git'
}
stage('java build')
{
sh '/opt/maven/bin/mvn clean deploy sonar:sonar -Dsonar.password=admin -Dsonar.login=admin'
}
  stage('Running java backend application')
{
             sh 'export JENKINS_NODE_COOKIE=dontKillMe ;nohup java -jar $WORKSPACE/target/*.jar &'
         }

}
