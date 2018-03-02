/**
 * In order to work with rabbitMQ in spring-boot we need to do this:
 * 1. Add spring-boot-starter-amqp (or simply add rabbit dependencies) to your project classpath
 * 2. install rabbit MQ (see here: https://www.rabbitmq.com/install-debian.html)
 * 3. configure rabbit MQ connection (see application.yml). This can be done almost automatically by spring-boot
 * 4. Configure queues (see {@link learn_to_code.springboot.rabbit.RabbitConfig}
 * 5. Create senders/receivers (see {@link learn_to_code.springboot.rabbit.Receiver}, {@link learn_to_code.springboot.rabbit.Sender})
 */
package learn_to_code.springboot.rabbit;