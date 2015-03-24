package com.baidar.androidChatter.toolBox;

import com.baidar.androidChatter.typo.InformacionDeMensaje;

public class ControladorMensaje {
	
	private static InformacionDeMensaje[] messagesInfo = null;
	
	public static void setMessagesInfo(InformacionDeMensaje[] messageInfo){
		ControladorMensaje.messagesInfo = messageInfo;
	}
	
	
	
	public static InformacionDeMensaje checkMessage(String username){
		InformacionDeMensaje result = null;
		if (messagesInfo != null) {
			for (int i = 0; i < messagesInfo.length;) {
					result = messagesInfo[i];
					break;
			}			
		}		
		return result;
	}
	
	



	public static InformacionDeMensaje getMessageInfo(String username){
		InformacionDeMensaje result = null;
		if (messagesInfo != null) {
			for (int i = 0; i < messagesInfo.length;) {
					result = messagesInfo[i];
					break;
			}			
		}		
		return result;
	}






	public static InformacionDeMensaje[] getMessagesInfo() {
		return messagesInfo;
	}



	
	
	

}
