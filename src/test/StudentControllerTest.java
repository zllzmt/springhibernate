import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration//表示测试的是controller web中的spring
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mvc.xml"})
public class StudentControllerTest {

    @Autowired
    WebApplicationContext wac;
    //controller测试核心类  mock:模拟
    MockMvc mockMvc;

    @Before//在所有测试执行之前，调用该方法 对mockmvc初始化
    public void setUp() throws Exception {
        mockMvc=MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getStudent() throws Exception {
        String json="{\"id\":1,\"name\":\"jack\",\"age\":12}";
        mockMvc.perform(get("/student/get.do").param("id","1").param("a","qwer"))
                .andDo(print())//打印该次请求的结果
                .andExpect(status().isOk())//期待方法调用成功
                .andExpect(content().string(json))
                .andReturn();

    }

    @Test
    public void hello() throws Exception {
        mockMvc.perform(post("/student/hello.do"))
                .andDo(print())
                .andExpect(status().isOk())//期待方法调用成功
                .andExpect(view().name("hello"))//期待视图的名字
                .andExpect(model().attribute("name","tom"))//期待model的值
                .andExpect(model().attribute("age",18));
    }
}