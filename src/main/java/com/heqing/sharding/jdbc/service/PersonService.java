package com.heqing.sharding.jdbc.service;

import com.heqing.sharding.jdbc.entity.Person;
import com.heqing.sharding.jdbc.util.PageInfoUtil;

import java.util.List;

/**
 * 用户业务逻辑层接口
 *
 * @author heqing
 * @email  975656343@qq.com
 * @date   2021-11-24 09:47:33
 */
public interface PersonService {

    /**
     * 增加用户实例
     *
     * @param person 用户实例
     * @return int 成功数量
     */
    int savePerson(Person person);

    /**
     * 增加多条用户实例
     *
     * @param personList 多条用户实例
     * @return int 成功数量
     */
    int savePerson(List<Person> personList);

    /**
     * 获取所有用户实例信息
     *
     * @return List<Person> 用户主键集合
     */
    List<Person> listPerson();

    /**
     * 根据条件获取多条用户实例
     *
     * @param person 用户实例:做条件查询使用
     * @return List<Person> 用户实例集合
     */
    List<Person> listPersonByParam(Person person);

    /**
     * 根据分页获取多条用户实例
     *
     * @param pageNum 第几页
     * @param pageSize 每页数量
     * @return Page<Person> 用户实例集合
     */
    PageInfoUtil<Person> listPersonByPage(int pageNum, int pageSize);

    /**
     * 根据分页及条件获取多条用户实例
     *
     * @param person 用户实例:做条件查询使用
     * @param pageNum 第几页
     * @param pageSize 每页数量
     * @return Page<Person> 用户实例集合
     */
    PageInfoUtil<Person> listPersonByParamAndPage(Person person, int pageNum, int pageSize);

    /**
     * 根据条件删除多条用户实例
     *
     * @param person 用户实例:做条件删除使用
     * @return int 成功数量
     */
    int deletePersonByParam(Person person);

    /**
     * 修改用户实例
     *
     * @param person 用户实例信息
     * @return int 成功数量
     */
    int updatePersonByKey(Person person);

    /**
     * 批量修改用户实例
     *
     * @param personList 多条用户实例信息
     * @return int 是否成功
     */
    int updatePersonByKey(List<Person> personList);


    /**
     * 根据主键获取用户实例信息
     *
     * @param id  用户实例
     * @return List<T> 用户实例信息
     */
    Person getPersonByKey(Long id);

    /**
     * 根据多个主键获取用户实例信息
     *
     * @param idList  用户实例集合
     * @return List<T> 用户实例信息
     */
    List<Person> listPersonByKey(List<Long> idList);

    /**
     * 根据主键删除多条用户实例
     *
     * @param id 主键
     * @return int 成功数量
     */
    int deletePersonByKey(Long id);

    /**
     * 根据多个主键删除多条用户实例
     *
     * @param idList 主键集合
     * @return int 成功数量
     */
    int deletePersonByKey(List<Long> idList);

}
