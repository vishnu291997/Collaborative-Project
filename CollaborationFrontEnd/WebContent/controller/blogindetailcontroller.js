/**
 * 
 */
app.controller('BlogInDetailCtrl',function($scope,$location,BlogPostService,$routeParams,$sce,NotificationCtrl){
	var id=$routeParams.id
	
	//statement //select * from blogpost where id=?
	BlogPostService.getBlog(id).then(function(response){
		$scope.blogPost=response.data//BlogPost object
		$scope.content=$sce.trustAsHtml($scope.blogPost.blogContent)
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	$scope.approveBlogPost=function(blogPost){
		BlogPostService.approveBlogPost(blogPost).then(function(response){
			NotificationCtrl.getNotificationNotViewed();
			$location.path('/blogswaitingforapproval')
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	$scope.rejectBlogPost=function(blogPost){
		console.log(blogPost)
		BlogPostService.rejectBlogPost(blogPost,$scope.rejectionReason).then(function(response){
			NotificationCtrl.getNotificationNotViewed();
			$location.path('/blogswaitingforapproval')
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.showTextArea=function(){
		$scope.isRejected=!$scope.isRejected
	}
	$scope.addComment=function(blog,commentTxt){
		if(commentTxt==undefined || commentTxt=="")
			$scope.error='please enter some comments.. '
			else 
		BlogPostService.addComment(blog,commentTxt).then(function(response){
			$scope.commentTxt=""
			$scope.error=""
			$scope.blogComment=response.data
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.getBlogComments=function(blogPostId){
		BlogPostService.getBlogComments(blogPostId).then(
				function(response){
					$scope.blogComments=response.data//result of query[select * from blogcomment where blogpost_id=?]
				},function(response){
					if(response.status==401)
						$location.path('/login')
				})
	}
	
})
