package com.niit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.collaborationbackend.ErrorClazz;
import com.niit.collaborationbackend.Friend;
import com.niit.collaborationbackend.User;
import com.niit.collaborationdao.FriendDao;
import com.niit.collaborationdao.UserDao;

@Controller
public class FriendController {
	@Autowired
private FriendDao friendDao;
	@Autowired
private UserDao userDao;
	@RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
	public ResponseEntity<?> getAllSuggestedUsers(HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		List<User> suggestedUsers=friendDao.getSuggestedUsers(email);
		return new ResponseEntity<List<User>>(suggestedUsers,HttpStatus.OK);
	}
	//create new friend object [id,toId,fromId,status]
	@RequestMapping(value="/friendrequest",method=RequestMethod.POST)
	public ResponseEntity<?> addFriendRequest(@RequestBody User friendRequestToId,HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		User fromId=userDao.getUser(email);
		Friend friend=new Friend();
		friend.setFromId(fromId);
		friend.setToId(friendRequestToId);
		friend.setStatus('P');
		friendDao.addFriendRequest(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	@RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
	public ResponseEntity<?> getPendingRequests(HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		List<Friend> pendingRequests=friendDao.getPendingRequests(email);
		return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
	}
	@RequestMapping(value="/acceptrequest",method=RequestMethod.PUT)
	public ResponseEntity<?> acceptFriendRequest(@RequestBody Friend friend,HttpSession session){
		System.out.println("Friend ID is "+friend.getId());
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		friend.setStatus('A');
		friendDao.acceptFriendRequest(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	@RequestMapping(value="/deleterequest",method=RequestMethod.PUT)
	public ResponseEntity<?> deleteFriendRequest(@RequestBody Friend friend,HttpSession session){
		System.out.println("Friend ID is "+friend.getId());
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		friendDao.deleteFriendRequest(friend);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@RequestMapping(value="/listoffriends",method=RequestMethod.GET)
	public ResponseEntity<?> listOfFriends(HttpSession session){
		String email=(String)session.getAttribute("loggedInUser");
		if(email==null){
			ErrorClazz errorClazz=new ErrorClazz(5,"Unauthorized access.. please login..");
			return new ResponseEntity<ErrorClazz>(errorClazz,HttpStatus.UNAUTHORIZED);
		}
		List<User> friendsDetails=friendDao.listOfFriends(email);
		return new ResponseEntity<List<User>>(friendsDetails,HttpStatus.OK);
	}

}
