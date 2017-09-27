package com.adel.dao.impl;

import org.springframework.stereotype.Repository;

import com.adel.dao.IUserDAO;
import com.adel.model.User;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements IUserDAO{

}
