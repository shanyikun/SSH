package com.syk.ssh.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.syk.ssh.entity.UserEntity;

@Repository("userDao")
public class UserDao {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	public Map<String, Object> login(String name, String password){
		Session session = sessionFactory.getCurrentSession();
		Object userEntity = null;
		Map<String, Object> result = new HashMap<String, Object>();
		System.out.println(name);
		System.out.println(password);
		String hql = "from UserEntity where name=:name and password=:password";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		query.setParameter("password", password);
		userEntity = query.uniqueResult();
		if(userEntity!=null) {
			result.put("statCode", "0");
			result.put("userEntity", (UserEntity) userEntity);
			return result;
		}
		else {
			result.put("statCode",  "500");
			result.put("message", "name or password is wrong!!!");
			return result;
		}
	}
	public Map<String, Object> register(String name, String password){
		Session session = sessionFactory.getCurrentSession();
		Object isExit = null;
		Map<String, Object> result = new HashMap<String, Object>();
		String hql = "from UserEntity where name=:name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		isExit = query.uniqueResult();
		if(isExit!=null) {
			result.put("statCode", "500");
			result.put("message", "user has exists!!!");
			return result;
		}
		else {
			UserEntity userEntity = new UserEntity(name, password);
			Integer id = (Integer) session.save(userEntity);
			userEntity.setId(id);
			result.put("statCode", "0");
			result.put("userEntity", userEntity);
			return result;
		}
	}
}
