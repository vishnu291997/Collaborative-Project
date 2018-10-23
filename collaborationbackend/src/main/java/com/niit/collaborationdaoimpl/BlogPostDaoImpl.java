package com.niit.collaborationdaoimpl;

import java.util.List;

import javax.transaction.Transactional;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.BlogPost;
import com.niit.collaborationdao.BlogPostDao;
@Repository
@Transactional
public class BlogPostDaoImpl implements BlogPostDao{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void addBlogPost(BlogPost blogPost) {
		 Session session=sessionFactory.getCurrentSession();
		  session.save(blogPost);
		
	}

	@Override
	public List<BlogPost> getApprovedBlogs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where approvalStatus=true");
		List<BlogPost> blogPosts=query.list();
		return blogPosts;
	}

	@Override
	public List<BlogPost> getBlogsWaitingForApproval() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from BlogPost where approvalStatus=false");
		List<BlogPost> blogPosts=query.list();
		return blogPosts;

	}

	@Override
	public BlogPost getBlogPost(int id) {
		Session session=sessionFactory.getCurrentSession();
		BlogPost blogPost=(BlogPost)session.get(BlogPost.class, id);
		return blogPost;
	}

	@Override
	public void updateBlogPost(BlogPost blogPost) {
		Session session=sessionFactory.getCurrentSession();
		session.update(blogPost);
	}

	@Override
	public void deleteBlogPost(BlogPost blogPost) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(blogPost);
		
	}

}
