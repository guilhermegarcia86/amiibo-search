print('---> CREATING DATABASE <---');

db = db.getSiblingDB('amiibo');

print('---> CREATING COLLECTION <---');

db.createCollection('amiibo');

print('---> SUCCESS TO RUN SCRIPT <---');