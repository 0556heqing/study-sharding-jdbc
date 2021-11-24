package com.heqing.sharding.jdbc.repository;

import com.heqing.sharding.jdbc.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户数据持久层接口
 *
 * @author heqing
 * @email  975656343@qq.com
 * @date   2021-11-24 09:47:33
 */
@Mapper
public interface PersonRepository {

    /**
     * 增加用户实例
     *
     * @param person 用户实例
     * @return int 成功数量
     */
    int savePerson(Person person);

    /**
     * 增加多条用户
     *
     * @param personList 多条用户列
     * @return int 成功数量
     */
    int saveBatchPerson(List<Person> personList);

    /**
     * 获取所有用户
     *
     * @return List<Person> 用户主键集合
     */
    List<Person> listPerson();

    /**
     * 根据条件获取多条用户
     *
     * @param person 用户列:做条件查询使用
     * @return List<Person> 用户列集合
     */
    List<Person> listPersonByParam(Person person);

    /**
     * 根据条件删除多条用户实例
     *
     * @param person 用户实例:做条件删除使用
     * @return int 成功数量
     */
    int deletePersonByParam(Person person);

    /**
     * 根据多个主键批量修改用户
     *
     * @param personList 多条用户列信息
     * @return int 是否成功
     */
    int updateBatchPersonByKey(List<Person> personList);

    /**
     * 根据主键修改用户
     *
     * @param person 用户列信息
     * @return int 成功数量
     */
    int updatePersonByKey(Person person);

    /**
     * 根据主键获取用户
     *
     * @param id 
     * @return Person 用户信息
     */
    Person getPersonByKey(@Param("id") Long id);

    /**
     * 根据主键删除用户
     *
    * @param id
     * @return int 成功数量
     */
    int deletePersonByKey(@Param("id") Long id);

    /**
     * 根据多个主键删除多条用户
     *
     * @param idList  用户列集合
     * @return int 成功数量
     */
    int deleteBatchPersonByKey(List<Long> idList);

    /**
     * 根据多个主键获取用户
     *
     * @param idList 用户列集合
     * @return List<T> 用户列实体信息
     */
    List<Person> listPersonByKey(List<Long> idList);

}
