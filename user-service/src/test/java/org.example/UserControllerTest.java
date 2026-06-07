//package org.example;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.dto.ResponseUser;
//import org.example.dto.RequestUser;
//import org.example.exception.UserNotFoundException;
//import org.example.service.UserService;
//import org.hamcrest.CoreMatchers;
//import org.junit.jupiter.api.Test;
//import org.mockito.BDDMockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//
//
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//
//@WebMvcTest
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//    @MockBean
//    private UserService userService;
//
//    @Test
//    public void givenRequestUser_whenSaveUser_thenResponseUser() throws Exception {
//        //given
//        ResponseUser requestUser = new ResponseUser("Nikolay");
//        RequestUser user = new RequestUser("Nikolay","email", 25);
//        BDDMockito.given(userService.saveUser(any(RequestUser.class))).willReturn(requestUser);
//
//        //when
//        ResultActions resultActions = mockMvc.perform(post("/api/user")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsBytes(user)));
//
//        //then
//        resultActions.andDo(MockMvcResultHandlers.print())
//                .andExpect(jsonPath("$.name", CoreMatchers.is("Nikolay")))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//    }
//
//    @Test
//    public void givenRequestUpdateUser_whenUpdateUser_thenRequestUpdateUser() throws Exception {
//        //given
//        ResponseUser requestUser = new ResponseUser("Nikolay");
//        RequestUser responseUser = new RequestUser("Nikolay","email",25);
//        BDDMockito.given(userService.updateUser(anyInt(),any(RequestUser.class))).willReturn(requestUser);
//
//        //when
//        ResultActions resultActions = mockMvc.perform(put("/api/user/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(responseUser)));
//
//        //then
//        resultActions.andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is("Nikolay")))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//
//    }
//
//    @Test
//    public void givenRequestUpdateUser_whenUpdateUser_thenNotFoundException() throws Exception {
//        //given
//        ResponseUser requestUser = new ResponseUser("Nikolay");
//        RequestUser responseUser = new RequestUser("Nikolay","email",25);
//        BDDMockito.given(userService.updateUser(anyInt(),any(RequestUser.class))).willThrow(new UserNotFoundException("User not found"));
//
//
//        //when
//        ResultActions resultActions = mockMvc.perform(put("/api/user/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(responseUser)));
//
//        //then
//        resultActions.andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().is(404));
//    }
//
//    @Test
//    public void givenRequestUser_whenGetUser_thenRequestUser() throws Exception {
//        //given
//        ResponseUser requestUser = new ResponseUser("Nikolay");
//        BDDMockito.given(userService.getUser(anyInt())).willReturn(requestUser);
//
//
//        //when
//        ResultActions resultActions = mockMvc.perform(get("/api/user/1")
//                .contentType(MediaType.APPLICATION_JSON));
//
//
//        //then
//        resultActions.andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name",CoreMatchers.is("Nikolay")))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void givenRequestUser_whenGetUser_thenNotFoundException() throws Exception {
//        //given
//        BDDMockito.given(userService.getUser(anyInt())).willThrow(new UserNotFoundException("User not found"));
//
//
//        //when
//        ResultActions resultActions = mockMvc.perform(get("/api/user/1")
//                .contentType(MediaType.APPLICATION_JSON));
//
//
//        //then
//        resultActions.andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().is(404));
//    }
//
//    @Test
//    public void givenRequestUser_whenDeleteUser_thenRequestUser() throws Exception {
//        //given
//        ResponseUser requestUser = new ResponseUser("Nikolay");
//        BDDMockito.given(userService.deleteUser(anyInt())).willReturn(requestUser);
//
//
//        //when
//        ResultActions resultActions = mockMvc.perform(delete("/api/user/1")
//                .contentType(MediaType.APPLICATION_JSON));
//
//
//        //then
//        resultActions.andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name",CoreMatchers.is("Nikolay")))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void givenRequestUser_whenDeleteUser_thenNotFoundException() throws Exception {
//        //given
//
//        BDDMockito.given(userService.deleteUser(anyInt())).willThrow(new UserNotFoundException("User not found"));
//
//
//        //when
//        ResultActions resultActions = mockMvc.perform(delete("/api/user/1")
//                .contentType(MediaType.APPLICATION_JSON));
//
//
//        //then
//        resultActions.andDo(MockMvcResultHandlers.print())
//                .andExpect(MockMvcResultMatchers.status().is(404));
//    }
//}
