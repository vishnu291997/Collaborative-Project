package com.niit.collaborationdaoimpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.ProfilePicture;
import com.niit.collaborationdao.ProfilePictureDao;
@Repository
@Transactional

public class ProfilePictureDaoImpl implements ProfilePictureDao{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void uploadProfilePicture(ProfilePicture profilePicture) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(profilePicture);

		
	}

	@Override
	public ProfilePicture getProfilePicture(String email) {
		Session session=sessionFactory.getCurrentSession();
		ProfilePicture profilePicture=(ProfilePicture)session.get(ProfilePicture.class, email);
		return profilePicture;
	}

}
