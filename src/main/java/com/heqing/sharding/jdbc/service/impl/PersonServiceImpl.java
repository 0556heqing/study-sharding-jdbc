package com.heqing.sharding.jdbc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heqing.sharding.jdbc.entity.Person;
import com.heqing.sharding.jdbc.repository.PersonRepository;
import com.heqing.sharding.jdbc.service.PersonService;
import com.heqing.sharding.jdbc.util.PageInfoUtil;
import com.heqing.sharding.jdbc.util.ValidateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户业务逻辑实现类
 *
 * @author heqing
 * @email  975656343@qq.com
 * @date   2021-11-24 09:47:33
 */
@Service("personService")
public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Autowired
	private PersonRepository personRepository;

    @Override
    public int savePerson(Person person) {
        boolean param = ValidateUtil.validateParameter(person);
        if(!param) {
            return 0;
        }
        return personRepository.savePerson(person);
    }

    @Override
    public int savePerson(List<Person> personList) {
        List<Person> temppersonList = new ArrayList<>();
        for(Person person : personList) {
            boolean param = ValidateUtil.validateParameter(person);
            if(param) {
                temppersonList.add(person);
            }
        }
        return personRepository.saveBatchPerson(temppersonList);
    }

    @Override
    public List<Person> listPerson() {
        return personRepository.listPerson();
    }

    @Override
    public List<Person> listPersonByParam(Person person) {
        return personRepository.listPersonByParam(person);
    }

    @Override
    public PageInfoUtil<Person> listPersonByPage(int pageNum, int pageSize) {
        pageNum = pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize < 1 ? 1 : pageSize > 100 ? 100 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Person> personPage = new PageInfo<Person>(personRepository.listPerson());
        return new PageInfoUtil(personPage.getList(), pageNum, pageSize, personPage.getTotal());
    }

    @Override
    public PageInfoUtil<Person> listPersonByParamAndPage(Person person, int pageNum, int pageSize) {
        pageNum = pageNum < 1 ? 1 : pageNum;
        pageSize = pageSize < 1 ? 1 : pageSize > 100 ? 100 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Person> personPage = new PageInfo<Person>(personRepository.listPersonByParam(person));
        return new PageInfoUtil(personPage.getList(), pageNum, pageSize, personPage.getTotal());
    }

    @Override
    public int deletePersonByParam(Person person) {
        return personRepository.deletePersonByParam(person);
    }

    @Override
    public int updatePersonByKey(Person person) {
        boolean param = ValidateUtil.validateParameter(person, "id");
        if(!param) {
            return 0;
        }
        return personRepository.updatePersonByKey(person);
    }

    @Override
    public int updatePersonByKey(List<Person> personList) {
        return personRepository.updateBatchPersonByKey(personList);
    }


    @Override
    public Person getPersonByKey(Long id) {
        return personRepository.getPersonByKey(id);
    }

    @Override
    public List<Person> listPersonByKey(List<Long> idList) {
        return personRepository.listPersonByKey(idList);
    }

    @Override
    public int deletePersonByKey(Long id) {
        return personRepository.deletePersonByKey(id);
    }

    @Override
    public int deletePersonByKey(List<Long> idList) {
        return  personRepository.deleteBatchPersonByKey(idList);
    }

}
