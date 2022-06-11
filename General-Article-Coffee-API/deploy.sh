gcloud builds submit --tag gcr.io/discoffery-352123/discoffery-service

gcloud run deploy discoffery-service \
  --image gcr.io/discoffery-352123/discoffery-service \
  --add-cloudsql-instances discoffery-352123:asia-southeast2:discofferycoffe \
  --update-env-vars INSTANCE_CONNECTION_NAME=discoffery-352123:asia-southeast2:discofferycoffe,DB_PASS=discoffery,DB_USER=rootR,DB_NAME=Discoffery \
  --platform managed \
  --region asia-southeast2 \
  --allow-unauthenticated \
  --project=discoffery-352123
