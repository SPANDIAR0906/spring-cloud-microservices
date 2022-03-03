package com.spandiar.thelibrarytransactions.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spandiar.thelibrarytransactions.model.BookRatings;
import com.spandiar.thelibrarytransactions.model.UserBase;

@Repository
public class UserRepository {
	
	@Autowired
	private EntityManager entity;
	
	private Logger logger = LoggerFactory.getLogger(UserRepository.class);
	
	@Transactional
	public UserBase createUser(UserBase user) {
		return entity.merge(user);
	}
	
	public boolean isUserNameAvailable(String userName) {
		
		Query doesUserNameExist = entity.createNativeQuery("select user_name from user_base where user_name = :username");
		doesUserNameExist.setParameter("username", userName);
		
		try {
			doesUserNameExist.getSingleResult();
		} catch (Exception ex) {
			logger.info("Exception Occured -> {} - {} ", ex.getClass(), ex.getMessage());
			if(ex.getClass() == NoResultException.class) {
				// requested userName is available
				return true;
			} 
		}
		return false;
	}
	
	public boolean doesUserExist(String userName) {
		
		Query doesUserExist = entity.createNativeQuery("select user_name from user_base where user_name = :username");
		doesUserExist.setParameter("username", userName);
		
		try {
			String result = (String) doesUserExist.getSingleResult();
			if(result != null && result.equalsIgnoreCase(userName)) {
				return true;
			}
		} catch (Exception ex) {
			logger.info("Exception occured -> {} ", ex.getMessage());
		}
		
		return false;
		
	}
	
	@Transactional
	public UserBase getUserInfo(String userName) {
		
		UserBase userInfo = null;
		Query getPrimaryKey = entity.createNativeQuery("select id from user_base where user_name = :username");
		getPrimaryKey.setParameter("username", userName);
		
		try {
			logger.info("Executing query to get the Primary Key for user -> {} ", userName);
			String primaryKey = (String) getPrimaryKey.getSingleResult();
			logger.info("Primary Key is -> {} ", primaryKey);
			userInfo = entity.find(UserBase.class, primaryKey);
		} catch (Exception ex) {
			logger.info("Exception occured -> {} ", ex.getMessage());
		}
		return userInfo;
	}
	
	@Transactional
	public BookRatings addBookRatingForUser(BookRatings rating) {
		return entity.merge(rating);
	}

}
