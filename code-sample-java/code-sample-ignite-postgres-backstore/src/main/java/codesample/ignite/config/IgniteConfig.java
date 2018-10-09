package codesample.ignite.config;

import codesample.ignite.cache.PersonCacheStore;
import codesample.ignite.entitry.Person;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.QueryEntity;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.communication.CommunicationSpi;
import org.apache.ignite.spi.communication.tcp.TcpCommunicationSpi;
import org.apache.ignite.spi.discovery.DiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.configuration.FactoryBuilder;
import java.util.Collections;
import java.util.LinkedHashMap;

@Configuration
public class IgniteConfig {

    @Bean
    Ignite igniteClient() throws IgniteException {
        return Ignition.start(igniteConfiguration());
    }

    @Bean
    public IgniteConfiguration igniteConfiguration() {
        IgniteConfiguration cfg = new IgniteConfiguration();
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
        CacheConfiguration cache = new CacheConfiguration();
        cache.setName("person");
        cache.setReadThrough(true);
        cache.setWriteThrough(true);

        cache.setCacheStoreFactory(FactoryBuilder.factoryOf(PersonCacheStore.class));
        cache.setQueryEntities(Collections.singleton(personQueryEntity()));

        return cache;
    }

    @Bean
    public TcpDiscoveryVmIpFinder tcpDiscoveryVmIpFinder() {
        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
        ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"));
        return ipFinder;
    }

    private QueryEntity personQueryEntity() {
        QueryEntity queryEntity = new QueryEntity();
        queryEntity.setKeyType(Long.class.getCanonicalName());
        queryEntity.setValueType(Person.class.getCanonicalName());
        queryEntity.setFields(personFields());

        return queryEntity;
    }

    private LinkedHashMap<String, String> personFields() {
        LinkedHashMap<String, String> fields = new LinkedHashMap<>();
        fields.put("id", Long.class.getCanonicalName());
        fields.put("name", String.class.getCanonicalName());
        return fields;
    }
}
