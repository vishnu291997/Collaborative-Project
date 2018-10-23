package com.niit.collaborationdaoimpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.User;
import com.niit.collaborationdao.UserDao;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	public UserDaoImpl(){
		System.out.println("UserDaoImpl bean is created");
	}
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public void registration(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.save(user);
		
	}

	@Override
	public boolean isEmailUnique(String email) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where email=?");
		query.setString(0, email);
		User user=(User)query.uniqueResult();
		if(user!=null){//duplicate email address
			return false;
		}else{//unique email address
			return true;
		}
	}

	@Override
	public User login(User user) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where email=? and password=?");
		query.setString(0, user.getEmail());
		query.setString(1, user.getPassword());
		return (User)query.uniqueResult();
	}
	
	public void updateUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.update(user);
		//update User_s190035 set password=?,firstname=?,lastname=?,phonenumber=?
		//, online_status=?,role=? where email=?
		
	}

	public User getUser(String email) {
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class, email);
		return user;
	}

}
