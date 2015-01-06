<?php

	$fromLogin = $_GET['fromlogin'];
	$creneau = $_GET['creneau'];
	$participants = $_GET['participants'];

	$headers = "From: reuniut@demic.eu\r\nReply-To: baptiste.abel@etu.utc.fr";
	$subject = "[RéuniUT] Notification de réunion de $fromLogin";

	$message = "Bonjour !\n\n$fromLogin aimerait effectuer une reunion avec vous le $creneau de ??h a ??h.\nSont egalement convies a cette reunion $participants.\n\nCordialement,\nLa Team ReuniUT\n\nPS : Ceci est un message automatique genere par l'application ReuniUT. Vous pouvez repondre a ce mail pour tout support technique.";

	$to = explode (",", $participants);
	
	foreach($to as $i =>$participant) {
		$i > 0;
	    //echo $participant.'@etu.utc.fr</br>';
		$mail_sent = @mail( $participant.'@etu.utc.fr', $subject, $message, $headers );
		echo $mail_sent ? "Mail sent" : "Mail failed";
	}

?>
