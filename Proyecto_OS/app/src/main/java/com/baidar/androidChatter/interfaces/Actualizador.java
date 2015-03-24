package com.baidar.androidChatter.interfaces;
import com.baidar.androidChatter.typo.InformacionDeAmigo;
import com.baidar.androidChatter.typo.InformacionDeMensaje;


public interface Actualizador {
	public void updateData(InformacionDeMensaje[] messages, InformacionDeAmigo[] friends, InformacionDeAmigo[] unApprovedFriends, String userKey);

}
