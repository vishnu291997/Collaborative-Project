/**
 * 
 */
app.controller('FriendCtrl',function($scope,$location,FriendService){
	//data from Controller To View
	//$scope.suggestedUsers has to get initialized automatically
	function getSuggestedUsers(){
	FriendService.getSuggestedUsers().then(function(response){
		$scope.suggestedUsers=response.data// List<User> [S in (A- (BUC))]
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	}
	
	$scope.sendFriendRequest=function(friendRequestToId){//toId is an User object,friend.setToId(toId)
		FriendService.sendFriendRequest(friendRequestToId).then(function(response){
			alert('Friend Request has been sent successfully..')
			getSuggestedUsers()
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	function getPendingRequests(){
		alert('entering getPendingRequests()')
		FriendService.getPendingRequests().then(function(response){
			$scope.pendingRequests=response.data//List<User>
			//response.data = [select f.fromId from Friend f where f.toId.email=? and status='P']
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.acceptRequest=function(pendingRequest){//pendingRequest is an Object of type Friend
		console.log(pendingRequest)
		FriendService.acceptRequest(pendingRequest).then(function(reponse){
			getPendingRequests()
		},
		function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	$scope.deleteRequest=function(pendingRequest){
		FriendService.deleteRequest(pendingRequest).then(function(response){
			getPendingRequests()
		},function(response){
			if(response.status==401)
				$location.path('/login')
		})
	}
	
	FriendService.listOfFriends().then(function(response){
		$scope.friendsDetails=response.data // List<User> [toId and fromId]
	},function(response){
		if(response.status==401)
			$location.path('/login')
	})
	
	
	getSuggestedUsers()
	getPendingRequests()
})
