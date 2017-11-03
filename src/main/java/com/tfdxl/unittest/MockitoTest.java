package com.tfdxl.unittest;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatcher;

import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author tianfeng
 * @date 2017/10/31
 */
public class MockitoTest {

    @Test
    public void simpleTest() {

        //设置mock对象
        List<String> mockedList = mock(List.class);
//        //设置方法的预期返回值
//        when(mockedList.get(0)).thenReturn("tianfeng");
//
//        //actual get
//        String name = mockedList.get(0);
//
//        //验证
//        verify(mockedList).get(0);
//
//        //junit测试
//        Assert.assertEquals("tianfeng", name);
//
//        when(mockedList.get(1)).thenThrow(new RuntimeException("test exception"));
//        try {
//            mockedList.get(1);
//        } catch (Exception e) {
//            System.err.print("get exception:" + e);
//        }

        when(mockedList.get(anyInt())).thenReturn("hello", "world");
        String result = mockedList.get(0) + mockedList.get(1);
        verify(mockedList, times(2)).get(anyInt());
        Assert.assertEquals("helloworld", result);
    }

    @Test
    public void argumentMatchersTest() {
        Map<Integer, String> map = mock(Map.class);
        when(map.put(anyInt(),anyString())).thenReturn("hello");
        map.put(1,"tianfeng");
        verify(map).put(eq(1),eq("tianfeng"));
    }

}

class IsListOfTwoElements extends ArgumentMatcher<List> {

    @Override
    public boolean matches(Object argument) {
        return ((List) argument).size() == 2;
    }
}
