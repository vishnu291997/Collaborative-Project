/**
 * 
 */
app.controller('BlogPostCtrl',function($scope,$location,BlogPostService,$rootScope){
	//View To BlogPostCtrl
	$scope.addBlogPost=function(blogPost){
		BlogPostService.addBlogPost(blogPost).then(function(response){
			alert('Blogpost inserted successfully and it is waiting for approval')
			$location.path('/home')
		},function(response){
			//Not Authenticated
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data
			//Exception while inserting blogpost object
		})
	}
	//LIST OF BLOGS APPROVED
	function getBlogsApproved(){
    BlogPostService.getBlogsApproved().then(function(response){
    	//response.data? -> List of blogs approved
    	$scope.blogsApproved=response.data
    },function(response){
    	if(response.status==401)
    		$location.path('/login')
    })
	}
	
	function getBlogsWaitingForApproval(){
		BlogPostService.getBlogsWaitingForApproval().then(function(response){
			$scope.blogsWaitingForApproval=response.data
		},function(response){
			if(response.status==401)
	    		$location.path('/login')
		})
	}	
	
	getBlogsApproved()
	//call the function only for ADMIN role
	if($rootScope.user.role=='ADMIN')
	getBlogsWaitingForApproval()
	
})
