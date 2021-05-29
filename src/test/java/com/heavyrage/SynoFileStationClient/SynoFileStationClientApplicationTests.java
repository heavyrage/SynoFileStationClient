package com.heavyrage.SynoFileStationClient;

import com.heavyrage.syno.apis.genericresponses.Error;
import com.heavyrage.syno.apis.genericresponses.Response;
import com.heavyrage.syno.apis.helper.QueryURLBuilder;
import com.heavyrage.syno.apis.model.auth.responses.LoginResponse;
import com.heavyrage.syno.apis.model.list.APIDescription;
import com.heavyrage.syno.apis.model.list.responses.APIDescriptionResponse;
import com.heavyrage.syno.client.SynoRestClient;
import com.heavyrage.syno.client.exceptions.AuthenticationFailureException;
import com.heavyrage.syno.client.exceptions.CompatibilityException;
import com.heavyrage.syno.client.exceptions.ResponseException;
import com.heavyrage.syno.model.UserSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class SynoFileStationClientApplicationTests {

	@Mock
	RestTemplate restTemplate;

	@Mock
	QueryURLBuilder queryURLBuilder;

	@InjectMocks
	SynoRestClient client;

	@Test
	void whenCorrectCredentials_thenShouldNotThrowException() {

		MockitoAnnotations.initMocks(this);
		queryURLBuilder = new QueryURLBuilder("baseURL");
		APIDescription auth = new APIDescription(1,1,"path");
		APIDescriptionResponse description = new APIDescriptionResponse(null, auth, null, null);
		queryURLBuilder.setApiDescription(description);

		String expectedSid = "blabla";
		Response response = new Response();
		response.setSuccess(true);
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setSid(expectedSid);
		response.setData(loginResponse);
		Mockito.when(queryURLBuilder.buildAuthenticationQuery(Mockito.anyString(), Mockito.anyString())).thenReturn("value");
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(response);

		this.client = new SynoRestClient(restTemplate);
		//ReflectionTestUtils.setField(this.client, "queryURLBuilder", queryURLBuilder);
		this.client.setQueryURLBuilder(this.queryURLBuilder);

		Exception exception = null;
		try {
			client.authenticate("Login", "Password");
		} catch (AuthenticationFailureException | CompatibilityException ex) {
			exception = ex;
		} finally {
			Assertions.assertNull(exception);
		}
	}

	@Test
	void whenWrongCredentials_thenShouldThrowException() {
		MockitoAnnotations.initMocks(this);
		queryURLBuilder = new QueryURLBuilder("baseURL");
		APIDescription auth = new APIDescription(1,1,"path");
		APIDescriptionResponse description = new APIDescriptionResponse(null, auth, null, null);
		queryURLBuilder.setApiDescription(description);

		Response response = new Response();
		response.setSuccess(false);
		Error error = new Error();
		error.setCode(400);
		response.setError(error);
		Mockito.when(queryURLBuilder.buildAuthenticationQuery(Mockito.anyString(), Mockito.anyString())).thenReturn("value");
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(response);

		Exception exception = null;

		this.client = new SynoRestClient(restTemplate);
		//ReflectionTestUtils.setField(this.client, "queryURLBuilder", queryURLBuilder);
		this.client.setQueryURLBuilder(this.queryURLBuilder);
		try {
			this.client.authenticate("wrongLogin", "wrongPassword");
		} catch (AuthenticationFailureException | CompatibilityException ex) {
			exception = ex;
		}

		Assertions.assertNotNull(exception);

	}

	@Test
	void whenLogoutFails_thenShouldThrowException() {
		MockitoAnnotations.initMocks(this);
		queryURLBuilder = new QueryURLBuilder("baseURL");
		APIDescription auth = new APIDescription(1,1,"path");
		APIDescriptionResponse description = new APIDescriptionResponse(null, auth, null, null);
		queryURLBuilder.setApiDescription(description);

		Response response = new Response();
		response.setSuccess(false);

		Mockito.when(queryURLBuilder.buildLogoutQuery()).thenReturn("value");
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(response);

		Exception exception = null;

		this.client = new SynoRestClient(restTemplate);
		//ReflectionTestUtils.setField(this.client, "queryURLBuilder", queryURLBuilder);
		this.client.setQueryURLBuilder(this.queryURLBuilder);
		try {
			this.client.logout();
		} catch (AuthenticationFailureException | CompatibilityException ex) {
			exception = ex;
		}

		Assertions.assertNotNull(exception);
	}

	@Test
	void whenListSharesDataTypeNotCorrect_ThenShouldThrowException() {
		MockitoAnnotations.initMocks(this);
		queryURLBuilder = new QueryURLBuilder("baseURL");
		APIDescription list = new APIDescription(1,1,"path");
		APIDescriptionResponse description = new APIDescriptionResponse(null,null, list, null);
		queryURLBuilder.setApiDescription(description);

		UserSession session = new UserSession("sid");
		queryURLBuilder.setUserSession(session);

		Response response = new Response();
		response.setSuccess(true);
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setSid("blabla");
		response.setData(loginResponse);

		Mockito.when(queryURLBuilder.buildListShareQuery()).thenReturn("value");
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(response);

		Exception exception = null;

		this.client = new SynoRestClient(restTemplate);
		//ReflectionTestUtils.setField(this.client, "queryURLBuilder", queryURLBuilder);
		this.client.setQueryURLBuilder(this.queryURLBuilder);
		try {
			this.client.listShares();
		} catch (ResponseException | CompatibilityException ex) {
			exception = ex;
		}

		Assertions.assertNotNull(exception);
	}

}
