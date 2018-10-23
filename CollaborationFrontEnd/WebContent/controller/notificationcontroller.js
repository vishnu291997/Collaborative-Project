/**
 * 
 */
app.controller('NotificationCtrl',function($scope,NotificationService,$routeParams,$location,$rootScope){
	
	var id=$routeParams.id
	console.log('In notification controller');
	
	NotificationService.getNotification(id).then(function(response){
		
		$scope.notification=response.data//select*from notification where id=?
		$rootScope.notificationCount = $rootScope.notifications.length;
	},function(response){
		
		if(response.data==401)
			$location.path('/login')
			
	})
	
	NotificationService.updateNotification(id).then(function(response){
		console.log('calling getnotifications');
		getNotificationNotViewed();
	
	},function(response){
		
		
		if(response.data==401)
			$location.path('/login')
	})
	
	function getNotificationNotViewed(){
		console.log('getting notifications');
		NotificationService.getNotificationNotViewed().then(function(response){
						$rootScope.notifications=response.data
			$rootScope.notificationCount=$rootScope.notifications.length
			console.log("notifications "+$rootScope.notifications+" notification count is "+$rootScope.notificationCount)
		},function(response){
			
			if(response.data==401)
				$location.path('/login')
			
		})
		
	}
	
	getNotificationNotViewed();
	
})