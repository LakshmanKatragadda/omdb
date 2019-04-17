import requests
import json
import sys

try:
 MovieName=sys.argv[1]
except NameError:
  print "Title not found"

url = 'http://www.omdbapi.com'

params = dict(
    apikey='8d6012',
)
params['t']=MovieName

resp = requests.get(url=url, params=params)
info = resp.json()

try:
  print('Movie :'+info['Title'])
except KeyError:
  print "Title not found"

try:
  print('IMDB Rating :'+info['imdbRating'])
except KeyError:
  print "IMDB Rating not found"




