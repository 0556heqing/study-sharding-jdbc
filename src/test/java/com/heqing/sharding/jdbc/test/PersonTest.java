package com.heqing.sharding.jdbc.test;

import com.alibaba.fastjson.JSON;
import com.heqing.sharding.jdbc.entity.Person;
import com.heqing.sharding.jdbc.service.PersonService;
import com.heqing.sharding.jdbc.util.PageInfoUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户数据持久层接口
 *
 * @author heqing
 * @email  975656343@qq.com
 * @date   2021-11-24 09:47:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {

    @Autowired
    PersonService personService;

    @Test
    public void testAllPerson() {
        List<Person> personList = personService.listPerson();
        personList.stream().map(personPO -> personPO.getName()).forEach(System.out::println);
    }

    @Test
    public void testListPersonByKey() {
        List<Long> keyList = new ArrayList<>();
        keyList.add(1L);
        keyList.add(2L);
        keyList.add(3L);
        keyList.add(5L);
        List<Person> personList = personService.listPersonByKey(keyList);
        personList.stream().map(personPO -> personPO.getName()).forEach(System.out::println);
    }

    @Test
    public void testListPersonByParamAndPage() {
        Person person = new Person();
//        person.setName("小");
        PageInfoUtil<Person> personList = personService.listPersonByParamAndPage(person, 1, 2);
        System.out.println("-->"+JSON.toJSONString(personList));
    }

    @Test
    public void testBatchSavePerson() {
        List<Person> personList = new ArrayList<>();
        for(int i=4;i<10;i++) {
            Person person = new Person();
            person.setId((long)i);
            person.setName(""+i);
            person.setSex(i);
            person.setBirthday(new Date());
            person.setAddrProvince(""+i);
            person.setRemark(""+i);
            personList.add(person);
        }
        System.out.println("-->"+personService.savePerson(personList));
    }

    @Test
    public void testUpdateBatchPersonByKey() {
        List<Person> personList = new ArrayList<>();
        for(int i=6;i<10;i++) {
            Person person = new Person();
            person.setId((long)(i-5));
            person.setName(""+i);
            person.setSex(i);
            person.setBirthday(new Date());
            person.setAddrProvince(""+i);
            person.setRemark(""+i);
            personList.add(person);
        }
        System.out.println("-->"+personService.updatePersonByKey(personList));
    }

    @Test
    public void testDeleteBatchPersonByKey() {
        List<Long> keyList = new ArrayList<>();
        keyList.add(1L);
        keyList.add(2L);
        System.out.println("-->"+personService.deletePersonByKey(keyList));
    }

}
