<h4> About </h4>
<p> Java/Kotlin/Scala/Python/TypeScript/Angular code samples </p>
<p> Some code may be quite outdated considering modern best practises. </p>
<p> Some parts way even not compile / not have test / be generally very junior because they were written an age ago</p>

<p> Currently consists of: </p>
<ul>
    <li><b>code-sample-angular-kotlin</b>. Kotlin + Angular app with JWT access/refresh token authentication and spring security roles management
    <li><b>code-sample-java</b>. Java code examples
        <ul>
            <li> <b> spring-boot </b> Spring boot examples
               <ul>
                  <li> <b> code-sample-java-springboot</b>. Consist of some (but not all) springboot features (RabbitMQ integration, reloadable properties)</li>
                  <li> <b>code-sample-java-springboot-ldap-login</b>. LDAP login with spring security example</li>
                  <li> <b> code-sample-spring-api-versioning</b>. Custom endpoint mapped based on request header "version" 
                  <li> <b> code-sample-spring-security</b>. Spring security features (Form login with Thymeleaf). </li>
                  <li> <b> code-sample-java-springboot-reactive-client-example</b>. How to use async WebFlux client to get responses from normal synchronous tomcat server</li>
               </ul>
            </li>
            <li> <b>code-sample-java-9</b>. Contains new stuff added in java 9 (including multi-module maven project, but excluding reactive API)</li>
            <li> <b>code-sample-java-10</b>. Contains new stuff added in java 10 </li>
            <li> <b>code-sample-java-11</b>. Contains new stuff added in java 11 </li>
            <li> <b>code-sample-ignite-postgres-backstore</b>. Contain example of Ignite being deployed over postgres DB 
            in cache mode. All data from postgres is loaded into ignite on startup and then used for in-memory computing. 
            Request for data creating/modification are propagated by Ignite to Postgres</li> 
            <li> <b> code-sample-java-algorithms-and-data-structures</b> Basic algorithms and data structures implemented on java. Data structures: Union, Queue, Stack. Algorithms: Binary Search </li>
            <li> <b> code-sample-java-aspectj</b>. Tiny aspectJ example</li>
            <li> <b> code-sample-java-ee</b>. Tiny jmx example </li>
            <li> <b> code-sample-java-hibernate</b>. Examples for most common hibernate features</li>
            <li> <b> code-sample-java-patterns</b>. Basic java implementation for most classical design patterns</li>
            <li> <b> code-sample-java-se</b>. Examples of some java SE APIs </li>
            <li> <b> code-sample-java-spring</b>. Tiny spring example. Uses only bean definition</li>
	    <li> <b> code-sample-java-useful-implementations</b>. Should Contain implementstions for some fun stuff. Currently contains nothing of interest </li>
         </ul>
    </li>
    <li><b>code-sample-kotlin</b>. Kotlin examples 
        <ul> 
            <li><b>code-sample-kotlin-basics</b>. Boring basic kotlin API stuff</li>
            <li><b>spring-boot</b>
                <ul>
                    <li><b>code-sample-kotlin-graphql</b>. GraphQL exampe with Kotlin </li>
                    <li><b>code-sample-kotlin-graphql</b>. GraphQL exampe with Kotlin using SPQR schema-from-code generator </li>
                    <li><b>code-sample-kotlin-spring-security</b>. Simple username-password authentication example </li>
                </ul>
            </li>
            <li><b>ktor</b>. Ktor sample applications
                <ul>
		    <li><b>hello_world</b>. Kotlin ktor example with docker</li>
                </ul>
            </li>
        </ul> 
    </li>
    <li><b>code-sample-scala</b>. Scala examples 
        <ul> 
            <li><b>scala-coursera</b> Scala specialization course materials from coursera: https://www.coursera.org/specializations/scala </li>
        </ul> 
    </li>
    <li><b>code-sample-python</b>. Python code examples. Currently consist of a single Flask example </li>
    <li><b>code-sample-docker</b>. Contains some docker examples
        <ul>
            <li><b>docker-official-getting-started</b>. Getting started guide from https://docs.docker.com/get-started</li>
            <li><b>code-sample-nginx-spring-boot-docker</b>. Spring-boot with nginx inside docker deployed with docker-compose
        </ul>
    </li>
    <li><b>code-sample-angular-java</b>. Angular app based on https://angular.io/tutorial .
    Uses real backend (with in-memory DB) </li>
    <li><b>code-sample-react/react-tic-tac-toe</b>. React example from official documentation </li>
</ul>

Some project have a dedicated README inside them.
