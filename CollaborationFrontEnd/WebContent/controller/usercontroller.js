/**
 * 
 */
app.controller('UserCtrl',function($scope,UserService,$location,$rootScope,$cookieStore){
	$scope.registration=function(user){
		console.log(user)
		UserService.registration(user).then(
				function(response){
					alert('Registered Successfully.. , please login again')
					$location.path('/login')
					
				},function(response){
				$scope.error=response.data	
				})
	}
	$scope.login=function(user){
		UserService.login(user).then(function(response){
			$cookieStore.put('userDetails',response.data)
			$rootScope.user=response.data // User Object
			$location.path('/home')
		},function(response){
			$scope.error=response.data
		})
	}
	$scope.updateProfile=function(user){
		UserService.updateProfile(user).then(function(response){
			$rootScope.user=response.data
			$cookieStore.put('userDetails',response.data)
			alert('Updated user profile successfully..')
			$location.path('/home')
		},function(response){
			if(response.status==401)
				$location.path('/login')
			$scope.error=response.data
		})
	}
	
})