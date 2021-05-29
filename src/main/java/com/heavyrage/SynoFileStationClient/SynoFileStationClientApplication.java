package com.heavyrage.SynoFileStationClient;

import com.heavyrage.syno.apis.helper.QueryURLBuilder;
import com.heavyrage.syno.apis.helper.SynoProperties;
import com.heavyrage.syno.client.SynoRestClient;
import com.heavyrage.syno.client.interceptors.SynoUploadHttpRequestInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication(scanBasePackages = "com.heavyrage")
@EnableConfigurationProperties(SynoProperties.class)
public class SynoFileStationClientApplication {

	private static final Logger log = LoggerFactory.getLogger(SynoFileStationClientApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(SynoFileStationClientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate template = builder.build();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL, MediaType.APPLICATION_OCTET_STREAM));
		messageConverters.add(converter);
		template.setMessageConverters(messageConverters);
		return template;
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate, SynoProperties synoProperties ) throws Exception {
		return args -> {
			//Scanner scanner = Scanner.getDevice();

			SynoRestClient client = new SynoRestClient(restTemplate);
			client.setQueryURLBuilder(new QueryURLBuilder(synoProperties.getBaseurl()));
			client.init();
			client.authenticate(synoProperties.getUsername(), synoProperties.getPassword());
			//client.createFolder("retest", "/scans/test/newtest");
			Resource resource = new ClassPathResource("facture.pdf");
			InputStream input = resource.getInputStream();
			File testFile = resource.getFile();
			client.uploadFile("facture.pdf", "/scans/test/newtest", testFile);
			client.logout();
		};
	}

}
