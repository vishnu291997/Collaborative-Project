package com.niit.collaborationdaoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaborationbackend.BlogComment;
import com.niit.collaborationdao.BlogCommentDao;


@Repository
@Transactional
public class BlogCommentDaoImpl implements BlogCommentDao  {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public void addBlogComment(BlogComment blogComment) {
		Session session=sessionFactory.getCurrentSession();
		session.save(blogComment);
		
	}

	@Override
	public List<BlogComment> getBlogComments(int blogPostId) {
		Session session=sessionFactory.getCurrentSession();
		//BlogComment  is an Entity
		// id,blogPost,user,commentTxt, commentedOn
		Query query=session.createQuery("from BlogComment where blogPost.id=?");
		query.setInteger(0, blogPostId);
		List<BlogComment> blogComments=query.list();
		return blogComments;
	}

	

}
