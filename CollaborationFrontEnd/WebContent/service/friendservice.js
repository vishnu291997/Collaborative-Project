/**
 * 
 */
app.factory('FriendService',function($http){
	var friendService={}
	var BASE_URL="http://localhost:8025/collaborationController"
	friendService.getSuggestedUsers=function(){
		return $http.get(BASE_URL + "/suggestedusers")
	}
	
	friendService.sendFriendRequest=function(toId){
		return $http.post(BASE_URL + "/friendrequest",toId)
	}
	
	friendService.getPendingRequests=function(){
		alert('entering getPendingRequests in service')
		return $http.get(BASE_URL + "/pendingrequests")
	}
	
	friendService.acceptRequest=function(pendingRequest){
		return $http.put(BASE_URL + "/acceptrequest",pendingRequest)
	}
	
	friendService.deleteRequest=function(pendingRequest){
		return $http.put(BASE_URL + "/deleterequest",pendingRequest)
	}
	
    friendService.listOfFriends=function(){
    	return $http.get(BASE_URL + "/listoffriends")
    }	
	return friendService;
})
