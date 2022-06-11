Documentation

Cloud Services
we used this following services as our infrastructure :
- Cloud Run to run the back-end of the apps 
- Cloud SQL (PostgreSQL) to store article data and it's reports 
- Cloud storage to store dcoker images in container registry

Deployment:

https://ibb.co/TWSNYN9

1.	Before deploying API on cloud run, we write code in nodejs for a function of the discoffery application, beginning with installing the framework, installing dependencies, connecting to the database, and writing code.
2.	Next, we create a mysql database in the sql cloud using an existing service to store prepared data.
3.	Then, in the following step, we create a dockerfile in which this docker is used. to create an image with a set of commands, instructions (arguments) to be executed sequentially and automatically
4.	We then submit the dockerfile image, along with the newly created local API, to Google Container Registry following this command:
    gcloud builds submit --tag gcr.io/discoffery-352123/discoffery-service
5.	Our final step is to create a cloud run service and configure it with cloud sql to be able to call data from the newly created mysql database, as well as other supporting dependencies such as selecting the image from the Google Container Registry to deploy the local API to the cloud run service following this commad :
    gcloud run deploy discoffery-service \
    --image gcr.io/discoffery-352123/discoffery-service \
    --add-cloudsql-instances discoffery-352123:asia-southeast2:discofferycoffe \
    --update-env-vars INSTANCE_CONNECTION_NAME=discoffery-352123:asia-southeast2:discofferycoffe,DB_PASS=discoffery,DB_USER=root,DB_NAME=Discoffery \
    --platform managed \
    --region asia-southeast2 \
    --allow-unauthenticated \
    --project=discoffery-352123
6.	Public API endpoints are now available for use in our application.


