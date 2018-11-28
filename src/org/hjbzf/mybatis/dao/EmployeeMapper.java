package org.hjbzf.mybatis.dao;

import org.hjbzf.mybatis.bean.Employee;

/**
 * 接口与配置文件可用动态绑定
 * @author zf
 * 2018年11月28日
 */
public interface EmployeeMapper {

	public Employee getEmpById( Integer id );
}
