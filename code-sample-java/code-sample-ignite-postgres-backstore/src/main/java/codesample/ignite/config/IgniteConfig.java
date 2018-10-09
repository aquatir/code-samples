package codesample.ignite.config;

import codesample.ignite.cache.PersonCacheStore;
import codesample.ignite.entitry.Person;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.communication.CommunicationSpi;
import org.apache.ignite.spi.communication.tcp.TcpCommunicationSpi;
import org.apache.ignite.spi.discovery.DiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.configuration.FactoryBuilder;
import java.util.Collections;

@Configuration
public class IgniteConfig {

    @Bean
    Ignite igniteClient() throws IgniteException {
        return Ignition.start(igniteConfiguration());
    }

    @Bean
    public IgniteConfiguration igniteConfiguration() {
        IgniteConfiguration cfg = new IgniteConfiguration();

        //cfg.setClientMode(true); Do not forget to set client mode if you need to
        cfg.setPeerClassLoadingEnabled(true);
        cfg.setDiscoverySpi(discoverySpi());
        cfg.setCommunicationSpi(communicationSpi());
        cfg.setCacheConfiguration(personCacheConfiguration());
        return cfg;
    }

    @Bean
    public DiscoverySpi discoverySpi() {

        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
        discoverySpi.setIpFinder(tcpDiscoveryVmIpFinder());

        return discoverySpi;
    }

    @Bean
    public CommunicationSpi communicationSpi() {
        TcpCommunicationSpi communicationSpi = new TcpCommunicationSpi();
        communicationSpi.setMessageQueueLimit(1024);
        return communicationSpi;
    }

    @Bean public CacheConfiguration personCacheConfiguration() {
        CacheConfiguration<Long, Person> cache = new CacheConfiguration<>("person");
        cache.setReadThrough(true);
        cache.setWriteThrough(true);
        cache.setCacheStoreFactory(FactoryBuilder.factoryOf(PersonCacheStore.class));

        return cache;
    }

    @Bean
    public TcpDiscoveryVmIpFinder tcpDiscoveryVmIpFinder() {
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"));
        return ipFinder;
    }
}
