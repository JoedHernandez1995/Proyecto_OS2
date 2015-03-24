<?php


class MySQL {	
	private $dbLink;
	private $dbHost;
	private $dbUsername;
    	private $dbPassword;
	private $dbName;
	public  $queryCount;
	
	function MySQL($dbHost,$dbUsername,$dbPassword,$dbName){
		$this->dbHost = $dbHost;
		$this->dbUsername = $dbUsername;
		$this->dbPassword = $dbPassword;
		$this->dbName = $dbName;	
		$this->queryCount = 0;		
	}

	function __destruct(){
		$this->close();
	}

	//connectarse a la base de datos
	private function connect() {	
		$this->dbLink = mysql_connect($this->dbHost, $this->dbUsername, $this->dbPassword);		
		if (!$this->dbLink)	{			
			$this->ShowError();
			return false;
		}
		else if (!mysql_select_db($this->dbName,$this->dbLink))	{
			$this->ShowError();
			return false;
		}
		else {
			mysql_query("set names latin5",$this->dbLink);
			return true;
		}
		unset ($this->dbHost, $this->dbUsername, $this->dbPassword, $this->dbName);		
	}	
	/************************************
	 * Metodo para terminar la conexion *
	 ************************************/
	function close(){
		@mysql_close($this->dbLink);
	}
	/*******************************************
	 * Revisa mySQL por si hay algun erro
	 * Si el error existe, entonces mostrarlo y retornar false
	 * sino, retornar true 
	 *******************************************/
	function ShowError(){
		$error = mysql_error();
		//echo $error;		
	}	
	/****************************
	 * Metodo para correr queries
	 ****************************/
	function  query($sql){	
		if (!$this->dbLink)	
			$this->connect();
			
		if (! $result = mysql_query($sql,$this->dbLink)) {
			$this->ShowError();			
			return false;
		}
		$this->queryCount++;	
		return $result;
	}
	/************************
	* Metodo para agarrar valores*
	*************************/
	function fetchObject($result){
		if (!$Object=mysql_fetch_object($result))
		{
			$this->ShowError();
			return false;
		}
		else
		{
			return $Object;
		}
	}
	/*************************
	* Metodo para el numero de filas
	**************************/
	function numRows($result){
		if (false === ($num = mysql_num_rows($result))) {
			$this->ShowError();
			return -1;
		}
		return $num;		
	}
	/*******************************
	 * Metodo para hacer salto de cadenas
	 *********************************/
	function escapeString($string){
		if (get_magic_quotes_gpc()) 
		{
			return $string;
		} 
		else 
		{
			$string = mysql_escape_string($string);
			return $string;
		}
	}
	
	function free($result){
		if (mysql_free_result($result)) {
			$this->ShowError();
			return false;
		}	
		return true;
	}
	
	function lastInsertId(){
		return mysql_insert_id($this->dbLink);
	}
	
	function getUniqueField($sql){
		$row = mysql_fetch_row($this->query($sql));
		
		return $row[0];
	}
	
	function testconnection() {	
		$this->dbLink = mysql_connect($this->dbHost, $this->dbUsername, $this->dbPassword);		
		if (!$this->dbLink)	{			
			$this->ShowError();
			return false;
		}
		else if (!mysql_select_db($this->dbName,$this->dbLink))	{
			$this->ShowError();
			return false;
		}
		else {
			mysql_query("set names latin5",$this->dbLink);
			return true;
		}
		unset ($this->dbHost, $this->dbUsername, $this->dbPassword, $this->dbName);		
	}		
}