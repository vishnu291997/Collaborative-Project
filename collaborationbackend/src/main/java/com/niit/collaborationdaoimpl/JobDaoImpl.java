package com.niit.collaborationdaoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.Job;
import com.niit.collaborationdao.JobDao;
@Repository
@Transactional
public class JobDaoImpl implements JobDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveJob(Job job) {
		Session session=sessionFactory.getCurrentSession();
		session.save(job);

	}

	@Override
	public List<Job> getAllJobs() {
		Session session=sessionFactory.getCurrentSession();
       Query query=session.createQuery("from Job");//HQL
        List<Job> jobs=query.list();
        return jobs;
	}

}
