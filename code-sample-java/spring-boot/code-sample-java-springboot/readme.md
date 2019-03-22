<h4> About </h4>

<p> Contains fancy stuff you can do with spring-boot </p>

<p> You can start exploring from pom.xml. Just read up the comments, they'll point you to fancy stuff</p>

<p> DOES require some configuration before being able to get launched as it uses databases, rabbitMQ and some different 
stuff. Configuration guide is not ready yet, but I'll do it anytime soon! </p>

<p> NOTE: DO NOT TRY TO BUILD JAVADOC from this project. It will not work due to some dangling 
javadoc comments. Those javadoc-style comments are here to allow you navigating between classes easily using IDE. 
This is not a bug, but a feature :) Use code + comments as documentation </p>

<p> Currenly have examples for: </p>

<ul>
    <li><b>Basic spring-boot</b>. Initialization with annotations, .yml config file, 
        @EnableConfigurationProperties annotations usage, @EnableCaching example </li>
    <li> <b>spring-boot-devtools</b>. Application autorestart features + how to do it in IDEA ide</li>
    <li> <b>spring-boot-starter-amqp</b>. Simple RabbitMQ example </li>
    <li> <b>spring-boot-starter-security</b>. Basic auth for single hardcoded user example (Pretty much - hello, world example)</li>
    <li> <b>spring-cloud-config-server</b>. Example of embedded config server </li>
    <li> <b>Config via external message soruce</b>. Uses Spring ReloadableResourceBundleMessageSource to load
    and REload property information </li>

</ul>