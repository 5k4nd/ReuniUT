<?php
	
	//si plusieurs arguments POST, on utilise un array
	//$posts = array(
	//	'username' => urlencode('abelbapt'),
	//	'password' => urlencode('yourPassword')
	//	);
	//url-ify the data for the POST
	//foreach($posts as $key=>$value) { $posts .= $key.'='.$value.'&'; }
	//rtrim($posts, '&'); //$posts = implode(‘&’, $posts);
	//curl_setopt($ch,CURLOPT_POST, count($posts)); //set number of POST vars

//unset($_GET);

//$_GET['login']='abelbapt';
//$_GET['password']='';
//$_POST['login']='abelbapt';
//$_POST['password']='';

if ( isset($_GET['login']) && isset($_GET['password']) )
{
	$login = $_GET['login'];
	$password = $_GET['password'];

	$service = 'http://wwwetu.utc.fr/sme/EDT/'.$login.'.edt';
	$myUSERAGENT = 'Mozilla/5.0 (X11; Linux x86_64; rv:34.0) Gecko/20100101 Firefox/34.0';
	//(attention, https rend certaines requêtes CAS instables)

	function httpRequest_get($URL, $myUSERAGENT) {
		$ch = curl_init($URL);

		curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1); //return the response rather than output it
		curl_setopt($ch, CURLOPT_USERAGENT, $myUSERAGENT);//proof we're not a bot

		$response = curl_exec($ch);

		if (!$response)
			echo "Erreur, requête non-envoyée";
		else
		{
			curl_close($ch);
			return $response;
		}
	}

	function httpAndPostRequest_getTGT($URL, $POST, $myUSERAGENT) {
		$ch = curl_init($URL);

		curl_setopt($ch, CURLOPT_HEADER, 1); //return HTTP headers with response
		curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);

		curl_setopt($ch,CURLOPT_POSTFIELDS, $POST);
		curl_setopt($ch, CURLOPT_USERAGENT, $myUSERAGENT);
		
		$response = curl_exec($ch);

		if (!$response)
			echo "Erreur, requête non-envoyée";
		else
		{
			list($headers, $body) = explode("\r\n\r\n", $response, 2);
			//$headers now has a string of the HTTP headers
			//$body is the body of the HTTP response

			$headers = explode("\n", $headers);
			$ourHeader = null;
			foreach($headers as $header)
			{
				//echo $header . "<br/>";
			    if (stripos($header, 'Location:') !== false)
			        $ourHeader = $header;
			}
			curl_close($ch);
			if ($ourHeader != null)
				return array($ourHeader, $body);
			else
				return -1;//si on a rien + pas de location... souci !
		}
	}

	function getTicketFromPost($ticket) {//extract TGT (Ticket Granting Ticket) location
		if ( substr($ticket, 0, 44) != 'Location: https://cas.utc.fr/cas/v1/tickets/' )
			return -1;
		else {
			$ticket = substr($ticket, 44);
			return $ticket;
		}
	}

	function httpAndPostRequest_get($URL, $POST, $myUSERAGENT) {//Service Ticket
		$ch = curl_init($URL);

		curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
		curl_setopt($ch,CURLOPT_POSTFIELDS, $POST);
		curl_setopt($ch, CURLOPT_USERAGENT, $myUSERAGENT);
		
		$response = curl_exec($ch);

		if (!$response)
			echo "Erreur, requête non-envoyée";
		else
		{
			curl_close($ch);
			return $response;
		}
	}

	function httpRequest_getEdt($service, $ticketST, $myUSERAGENT) {
		$URL = "$service?ticket=$ticketST"; //Use &ticket if $service already has query parameters
		echo '<br/>url : '.$URL;
		$JSESSIONID = tempnam('/tmp','cookie');
		
		$ch = curl_init($URL);
		curl_setopt($ch, CURLOPT_COOKIEJAR, $JSESSIONID);
		//curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
		//curl_setopt($ch, CURLOPT_USERAGENT, $myUSERAGENT);
		
		$response = curl_exec($ch);

		$ch = curl_init ($URL);
	 	curl_setopt ($ch, CURLOPT_COOKIEFILE, $JSESSIONID);
 		curl_setopt ($ch, CURLOPT_RETURNTRANSFER, true);
 
 		echo "$$$".$JSESSIONID."$$$";

 		$response = curl_exec ($ch);
 		echo $response;

		if (!$response)
			echo "Erreur, requête non-envoyée";
		else
		{
			//curl_close($ch);
			return $response;
		}
	}


//CORPS DU PROGRAMME

//test du login + get ticket TGT
	if ( !($response = httpAndPostRequest_getTGT('https://cas.utc.fr/cas/v1/tickets', 'username='.$login.'&password='.$password, $myUSERAGENT)) )
		throw new Exception('Requête vers le CAS impossible. Celui-ci a été modifié ?');
	if ($response == -1)
		echo "Logins mauvais.";//ou erreur admin de CAS...
	else
	{
		echo "correct";
		
		//echo "<br/>======================<br/>======================<br/>";
		system('./cas.py > /dev/null'); //to hide the output

		/* pour la beta, tout ce qui suit se fait via le script python qui -- lui -- fonctionne... (fail des cookies avec PHP5 pour le moment)

		if ( isset($_POST['getEDT']) ) if ( $_POST['getEDT'] == 'true' )
		{
			$ticketTGT = getTicketFromPost($response[0], $myUSERAGENT);
			if ($ticketTGT == -1)
				echo "Erreur : le système de tickets a été modifié depuis la construction de ce script. Contactez un admin.";
			else
			{//récupération du ticket ST après envoi du TGT au service
				echo "<br/>======================<br/>";
				echo $ticketTGT;

				$POST = 'service='.$service;
				$ticketST = httpAndPostRequest_get('https://cas.utc.fr/cas/v1/tickets/'.$ticketTGT, $POST, $myUSERAGENT);
				echo "<br/>======================<br/>";
				echo $ticketST;				
				
				$XMLuser = httpRequest_get("https://cas.utc.fr/cas/serviceValidate?service=$service&ticket=$ticketST", $myUSERAGENT);
				echo "<br/>======================<br/>";
				echo $XMLuser;

				$edt = httpRequest_getEdt($service, $ticketST, $myUSERAGENT);
				echo "<br/>======================<br/>";
				echo $edt;
				stockEDT(); //range login.edt dans EDT/
				
			}
		}
		//on sort de getEDT
		*/
	}
}
else
	echo "Erreur de requête";


?>
