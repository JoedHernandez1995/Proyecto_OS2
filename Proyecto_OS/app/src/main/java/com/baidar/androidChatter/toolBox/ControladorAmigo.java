package com.baidar.androidChatter.toolBox;

import com.baidar.androidChatter.typo.InformacionDeAmigo;

/*
 * Esta clase puede almacenar informacion de amigos y revisar la clave de usuario y el nombre de usario
 * dependiendo de la informacion almacenada
 */
public class ControladorAmigo {
	
	private static InformacionDeAmigo[] friendsInfo = null;
	private static InformacionDeAmigo[] unapprovedFriendsInfo = null;
	private static String activeFriend;
	
	public static void setFriendsInfo(InformacionDeAmigo[] friendInfo){
		ControladorAmigo.friendsInfo = friendInfo;
	}
	
	
	
	public static InformacionDeAmigo checkFriend(String username, String userKey){
		InformacionDeAmigo result = null;
		if (friendsInfo != null) {
			for (int i = 0; i < friendsInfo.length; i++) {
				if (friendsInfo[i].userName.equals(username) && friendsInfo[i].userKey.equals(userKey)){
					result = friendsInfo[i];
					break;
				}				
			}			
		}		
		return result;
	}
	
	public static void setActiveFriend(String friendName){
		activeFriend = friendName;
	}
	
	public static String getActiveFriend(){
		return activeFriend;
	}



	public static InformacionDeAmigo getFriendInfo(String username){
		InformacionDeAmigo result = null;
		if (friendsInfo != null) {
			for (int i = 0; i < friendsInfo.length; i++) {
				if ( friendsInfo[i].userName.equals(username) ){
					result = friendsInfo[i];
					break;
				}				
			}			
		}		
		return result;
	}



	public static void setUnapprovedFriendsInfo(InformacionDeAmigo[] unapprovedFriends) {
		unapprovedFriendsInfo = unapprovedFriends;		
	}

	public static InformacionDeAmigo[] getFriendsInfo() {
		return friendsInfo;
	}

	public static InformacionDeAmigo[] getUnapprovedFriendsInfo() {
		return unapprovedFriendsInfo;
	}
	
	
	

}
