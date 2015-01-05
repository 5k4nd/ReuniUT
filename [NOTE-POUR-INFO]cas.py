#!/usr/bin/python
# -*- coding: UTF-8 -*-

import sys
import os.path
import httplib, urllib, urllib2, cookielib

#login = 'abelbapt'
#password = ''
login = sys.argv[1]
password = sys.argv[2]

class main:
	headers = {"Content-type": "application/x-www-form-urlencoded", "Accept": "text/plain"}

def httpAndPostRequest_getTGT(login, password):
	POST = urllib.urlencode({'username': login, 'password': password})
	connect = httplib.HTTPSConnection("cas.utc.fr")
	connect.request("POST", "/cas/v1/tickets/", POST, main.headers)
	response = connect.getresponse()
	#body = response.read()
	connect.close()
	return response

def httpAndPostRequest_getST(service, ticketTGT):
	POST = urllib.urlencode({'service': service })
	connect = httplib.HTTPSConnection("cas.utc.fr")
	connect.request("POST", "/cas/v1/tickets/%s" % ( ticketTGT ), POST, main.headers)
	response = connect.getresponse()
	#print response.status, response.reason
	ticketST = response.read()
	connect.close()
	return ticketST

def getEdt(service, ticketST):
	url = "%s?ticket=%s" % ( service, ticketST ) # Use &ticket if service already has query parameters
	
	JSESSIONID = cookielib.CookieJar()
	
	cookie_handler = urllib2.HTTPCookieProcessor(JSESSIONID)
	
	opener = urllib2.build_opener(cookie_handler, urllib2.HTTPHandler(debuglevel=1))
	
	urllib2.install_opener(opener)

	# on envoie la requête
	ticketST_response = urllib2.urlopen(url).read()

	return urllib2.urlopen(service).read()



print "CAS UTC\n@littleDad - 2014\n"

# getTGT (Grab the Ticket Granting Ticket)
response = httpAndPostRequest_getTGT(login, password)
#print response.status, response.reason

# on extrait le TGT de la réponse
location = response.getheader('location')
ticketTGT = location[location.rfind('/') + 1:]

print ticketTGT
print "======================"


# on récupère le ST (Service Ticket) d'un service (ici sme-EDT) à partir du TGT
service = 'http://wwwetu.utc.fr/sme/EDT/%s.edt' % login
ticketST = httpAndPostRequest_getST(service, ticketTGT)

print ticketST
print "======================"


# on récupère l'edt
edt = getEdt(service, ticketST)
print edt
file = open('EDT/'+login+'.edt', 'w') #pas besoin de tester l'existence, un edt récent l'emporte toujours sur un vieux
file.write (edt)