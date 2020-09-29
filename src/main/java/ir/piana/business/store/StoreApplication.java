package ir.piana.business.store;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import ir.piana.business.store.action.ActionProperties;
import ir.piana.business.store.cfg.StaticResourceProperties;
import ir.piana.business.store.rest.ImageLoaderProperties;
import ir.piana.business.store.service.sql.SqlProperties;
import ir.piana.business.store.service.storage.StorageProperties;
import ir.piana.business.store.service.store.StoreMenuProperties;
import ir.piana.business.store.util.LowerCaseKeyDeserializer;
import ir.piana.business.store.util.LowerCaseKeySerializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;

@SpringBootApplication
//@ServletComponentScan("")
@EnableTransactionManagement
@EnableCaching
@EnableConfigurationProperties({
		ActionProperties.class,
		StorageProperties.class,
		SqlProperties.class,
		ImageLoaderProperties.class,
		StaticResourceProperties.class,
		StoreMenuProperties.class
})
public class StoreApplication {

	@Bean("objectMapper")
	public ObjectMapper getObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		SimpleModule module = new SimpleModule("LowerCaseKeyDeserializer",
				new Version(1,0,0,null));
		module.addKeyDeserializer(Object.class, new LowerCaseKeyDeserializer());
		module.addKeySerializer(Object.class, new LowerCaseKeySerializer());
		objectMapper.registerModule(module);
		return objectMapper;
	}

//	@Bean("objectMapper")
//	public ObjectMapper getObjectMapper() {
//		ObjectMapper objectMapper = new ObjectMapper();
////		SimpleModule module = new SimpleModule("LowerCaseKeyDeserializer",
////				new Version(1,0,0,null));
////		module.addKeyDeserializer(Object.class, new LowerCaseKeyDeserializer());
////		module.addKeySerializer(Object.class, new LowerCaseKeySerializer());
////		objectMapper.registerModule(module);
//		return objectMapper;
//	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public CacheManager cacheManager() {
		return new HazelcastCacheManager();
	}

	@Bean
	CommandLineRunner init() {
		return (args) -> System.out.println("Start...");
	}

	public static void main(String[] args) {



		SpringApplication.run(StoreApplication.class, args);
	}

}
