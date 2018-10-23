/**
 * 
 */
app.factory('NotificationService',function($http){
	console.log('In notification service');
	var notificationService={}
	
	notificationService.getNotification=function(id){
		
		 var url="http://localhost:8025/collaborationController/notification/"+id 
		return $http.get(url,id)
	}
	
	notificationService.updateNotification=function(id){
		var url="http://localhost:8025/collaborationController/updatenotification/"+id
	     return $http.put(url,id)
	}
	
	notificationService.getNotificationNotViewed=function()
	{
		var url="http://localhost:8025/collaborationController/notifications"
		return $http.get(url)
	}
	
	return notificationService;
	
	
})