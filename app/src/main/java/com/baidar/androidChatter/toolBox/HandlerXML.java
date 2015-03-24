package com.baidar.androidChatter.toolBox;

import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.baidar.androidChatter.interfaces.Actualizador;
import com.baidar.androidChatter.typo.InformacionDeAmigo;
import com.baidar.androidChatter.typo.InformacionDeEstado;
import com.baidar.androidChatter.typo.InformacionDeMensaje;

import android.util.Log;

/*
 * Parsea la informacion XML a un arreglo de InformacionDeAmigo
 * XML Structure 
 * <?xml version="1.0" encoding="UTF-8"?>
 * 
 * <friends>
 * 		<user key="..." />
 * 		<friend username="..." status="..." IP="..." port="..." key="..." expire="..." />
 * 		<friend username="..." status="..." IP="..." port="..." key="..." expire="..." />
 * </friends>
 *
 *
 *status == online || status == unApproved
 * */

public class HandlerXML extends DefaultHandler {
		private String userKey = new String();
		private Actualizador updater;
		
		public HandlerXML(Actualizador updater) {
			super();
			this.updater = updater;
		}

		private Vector<InformacionDeAmigo> mFriends = new Vector<InformacionDeAmigo>();
		private Vector<InformacionDeAmigo> mOnlineFriends = new Vector<InformacionDeAmigo>();
		private Vector<InformacionDeAmigo> mUnapprovedFriends = new Vector<InformacionDeAmigo>();
		
		private Vector<InformacionDeMensaje> mUnreadMessages = new Vector<InformacionDeMensaje>();

		
		public void endDocument() throws SAXException {
			InformacionDeAmigo[] friends = new InformacionDeAmigo[mFriends.size() + mOnlineFriends.size()];
			InformacionDeMensaje[] messages = new InformacionDeMensaje[mUnreadMessages.size()];
			
			int onlineFriendCount = mOnlineFriends.size();			
			for (int i = 0; i < onlineFriendCount; i++) {
				friends[i] = mOnlineFriends.get(i);
			}
			
						
			int offlineFriendCount = mFriends.size();			
			for (int i = 0; i < offlineFriendCount; i++) {
				friends[i + onlineFriendCount] = mFriends.get(i);
			}
			
			int unApprovedFriendCount = mUnapprovedFriends.size();
			InformacionDeAmigo[] unApprovedFriends = new InformacionDeAmigo[unApprovedFriendCount];
			
			for (int i = 0; i < unApprovedFriends.length; i++) {
				unApprovedFriends[i] = mUnapprovedFriends.get(i);
			}
			
			int unreadMessagecount = mUnreadMessages.size();
			//Log.i("MessageLOG", "mUnreadMessages="+unreadMessagecount );
			for (int i = 0; i < unreadMessagecount; i++) {
				messages[i] = mUnreadMessages.get(i);
				Log.i("MessageLOG", "i="+i );
			}
			
			this.updater.updateData(messages, friends, unApprovedFriends, userKey);
			super.endDocument();
		}		
		
		public void startElement(String uri, String localName, String name,Attributes attributes) throws SAXException {
			if (localName == "friend"){
				InformacionDeAmigo friend = new InformacionDeAmigo();
				friend.userName = attributes.getValue(InformacionDeAmigo.USERNAME);
				String status = attributes.getValue(InformacionDeAmigo.STATUS);
				friend.ip = attributes.getValue(InformacionDeAmigo.IP);
				friend.port = attributes.getValue(InformacionDeAmigo.PORT);
				friend.userKey = attributes.getValue(InformacionDeAmigo.USER_KEY);
				//friend.expire = attributes.getValue("expire");
				
				if (status != null && status.equals("online")){
					friend.status = InformacionDeEstado.ONLINE;
					mOnlineFriends.add(friend);
				}
				else if (status.equals("unApproved")){
					friend.status = InformacionDeEstado.UNAPPROVED;
					mUnapprovedFriends.add(friend);
				}	
				else{
					friend.status = InformacionDeEstado.OFFLINE;
					mFriends.add(friend);	
				}											
			}
			else if (localName == "user") {
				this.userKey = attributes.getValue(InformacionDeAmigo.USER_KEY);
			}
			else if (localName == "message") {
				InformacionDeMensaje message = new InformacionDeMensaje();
				message.userid = attributes.getValue(InformacionDeMensaje.USERID);
				message.sendt = attributes.getValue(InformacionDeMensaje.SENDT);
				message.messagetext = attributes.getValue(InformacionDeMensaje.MESSAGETEXT);
				Log.i("MessageLOG", message.userid + message.sendt + message.messagetext);
				mUnreadMessages.add(message);
			}
			super.startElement(uri, localName, name, attributes);
		}

		@Override
		public void startDocument() throws SAXException {			
			this.mFriends.clear();
			this.mOnlineFriends.clear();
			this.mUnreadMessages.clear();
			super.startDocument();
		}
		
		
}

