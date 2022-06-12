# DICOFFERY-APP-BACKEND


### General Info
This is Backend repository for Database Article Based on Coffee for showing Article About Coffee. Using the Javascript programming language by using the Node.js libraries, ORM Sequelieze and Framework Express.

> My Dependencies For Article Based on Coffee
```
"dependencies": {
    "body-parser": "^1.20.0",
    "docker": "^1.0.0",
    "express": "^4.18.1",
    "merge-descriptors": "^1.0.1",
    "morgan": "^1.10.0",
    "mysql": "^2.18.1",
    "mysql2": "^2.3.3",
    "nodemon": "^2.0.16",
    "qs": "^6.10.5",
    "sequelize": "^6.20.1"
 ```
    
### Technologies
Project is Created with : 
<ul>
  <li>Node Version v16.14.0</li>
  <li>Npm version 8.3.1</li>
  <li>Google Cloud Sql: Mysql 8.0</li>
  <li>Cloud Run/li>
  <li>DockerFile</li>
</ul>

### Create Google Cloud SQL (MYSQL)
<ol>
  <li>Create and setup your Cloud SQL</li>
  <li> Open Folder Database </li>
  <li>Import db.sql to cloud sql</li>
</ol>

### Deploy To GCP
Follow the Step to run on GCP

> Clone Repository
``` bash
# Clone Your Repository 
$ git clone https://github.com/Satrio-Arif/Discoffery.git
#Go to Directory
$ cd Artikel-Based-on-Coffee
```
> Configure Database for Article Based on Coffee
```
- Open folder database => db.js
- Change Configuration With Your Database (This App Using MySQL) : 
  var connection = new Sequelize('CHANGE_WITH_YOUR_NAME_DATABASE', 'CHANGE_WITH_YOUR_USER_DATABASE', 'CHANGE_WITH_YOUR_PASSWORD_DATABASE', {
    dialect: 'CHANGE_WITH_YOUR_DATABASE', 'Example: SQL, MongoDb, etc',
    host: 'CHANGE_WITH_YOUR_HOST_DATABASE',
```

> Configure Node.js
``` bash
# Reinstall The node_modules
$ npm install
# Test your App (Don't Forget Change Your Port)
$ npm start
```


> Deploy Docker to Google Cloud Run For Article Based on Coffee
```
- Before Deploy You Can Change The Dockerfile Configuration If You Have Spesific Configuration
- For Article Based On Coffee, I am using this configuration. You Can Changes If You Have Spesific Configuration :

FROM node:12-slim
WORKDIR /app
COPY  package.json package*.json 
RUN npm install
COPY . .
CMD ["npm", "start"]

- Click Cloud Shell Editor, then Upload Your Files
- Open Editor
- type the command: docker build -t repo : tag
example : docker build -t gcr.io/discoffery-352123/coffee:v5 .
- type the command : "gcloud project list" 
- type the command : "docker images"
- then select the image that will be built on the docker, then type the command docker push
example :
. docker push gcr.io/discoffery-352123/coffee:v5

If so, you can check it in the Container Registry

After that, go to cloud run services then build image dan set configuration. Finished


```
