package com.heqing.sharding.jdbc.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 * 
 * @author heqing
 * @email  975656343@qq.com
 * @date   2021-11-24 09:47:33
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
	 * id 
	 */
	@JSONField(name="id")
	private Long id;

    /**
	 * name 姓名
	 */
	@JSONField(name="name")
	private String name;

    /**
	 * sex 性别
	 */
	@JSONField(name="sex")
	private Integer sex;

    /**
	 * birthday 生日
	 */
    @JSONField(name="birthday", format="yyyy-MM-dd HH:mm:ss")
	private Date birthday;

    /**
	 * addrProvince 省份
	 */
	@JSONField(name="addr_province")
	private String addrProvince;

    /**
	 * remark 备注
	 */
	@JSONField(name="remark")
	private String remark;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
	    return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
	    return name;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSex() {
	    return sex;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getBirthday() {
	    return birthday;
	}

	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}

	public String getAddrProvince() {
	    return addrProvince;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark() {
	    return remark;
	}

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
