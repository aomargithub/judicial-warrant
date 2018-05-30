package com.informatique.gov.judicialwarrant.rest.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.informatique.gov.judicialwarrant.rest.dto.UserDto;
import com.informatique.gov.judicialwarrant.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserService userService;

	private final String URL = "/users/";

	@Test
	public void testAddUser() throws Exception {

		// prepare data and mock's behaviour
		UserDto userStub = new UserDto();
		when(userService.createUserInternal(any(UserDto.class))).thenReturn(userStub);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.objectToJson(userStub))).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.CREATED.value(), status);

		// verify that service method was called once
		verify(userService).createUserInternal(any(UserDto.class));

		UserDto resultUser = TestUtils.jsonToObject(result.getResponse().getContentAsString(), UserDto.class);
		assertNotNull(resultUser);
		assertEquals(1l, resultUser.getId().longValue());

	}

	@Test
	public void testGetUser() throws Exception {

		// prepare data and mock's behaviour
		UserDto userStub = new UserDto();
		when(userService.getById(any(Integer.class))).thenReturn(userStub);

		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL + "{id}", new Long(1)).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(userService).getById(any(Integer.class));

		UserDto resultUser = TestUtils.jsonToObject(result.getResponse().getContentAsString(), UserDto.class);
		assertNotNull(resultUser);
		assertEquals(1l, resultUser.getId().longValue());
	}

	@Test
	public void testGetUserNotExist() throws Exception {

		// prepare data and mock's behaviour
		// Not Required as employee Not Exist scenario

		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL + "{id}", new Long(1)).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND.value(), status);

		// verify that service method was called once
		verify(userService).getById(any(Integer.class));

		UserDto resultUser = TestUtils.jsonToObject(result.getResponse().getContentAsString(), UserDto.class);
		assertNotNull(resultUser);
	}

	@Test
	public void testGetAllUsers() throws Exception {

		// prepare data and mock's behaviour
		List<UserDto> userList = buildEmployees();
		when(userService.getAll()).thenReturn(userList);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(userService).getAll();

		// get the List<Employee> from the Json response
		
		@SuppressWarnings("unchecked")
		List<UserDto> userListResult = TestUtils.jsonToList(result.getResponse().getContentAsString());

		assertNotNull("Users not found", userListResult);
		assertEquals("Incorrect User List", userList.size(), userListResult.size());

	}

	@Test
	public void testDeleteUser() throws Exception {
		// prepare data and mock's behaviour
		UserDto userStub = new UserDto();
		userStub.setId(1);
		when(userService.getById(any(Integer.class))).thenReturn(userStub);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(URL + "{id}", new Long(1))).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.GONE.value(), status);

		// verify that service method was called once
		verify(userService).delete(any(Integer.class));

	}

	@Test
	public void testUpdateEmployee() throws Exception {
		// prepare data and mock's behaviour
		// here the stub is the updated employee object with ID equal to ID of
		// employee need to be updated
		UserDto userStub = new UserDto();
		when(userService.getById(any(Integer.class))).thenReturn(userStub);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).content(TestUtils.objectToJson(userStub))).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(userService).update(any(UserDto.class));

	}

	private List<UserDto> buildEmployees() {
		UserDto u1 = new UserDto();
		u1.setId(1);
		u1.setLoginName("attia");
		UserDto u2 = new UserDto();
		u2.setId(1);
		u2.setLoginName("attia");
		List<UserDto> userList = Arrays.asList(u1, u2);
		return userList;
	}

}
