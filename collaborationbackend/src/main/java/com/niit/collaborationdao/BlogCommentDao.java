package com.niit.collaborationdao;

import java.util.List;

import com.niit.collaborationbackend.BlogComment;

public interface BlogCommentDao {
	void addBlogComment(BlogComment blogComment );
	List<BlogComment> getBlogComments(int blogPostId);
}
