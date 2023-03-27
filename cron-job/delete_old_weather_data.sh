#!/bin/bash
set -e
while [ true ]
do
  echo "$(date): Deleting old weather data..." >> cron-job/delete_old_weather_data.log
  mysql -u ${DB_USERNAME} -p${DB_PASSWORD} -h ${DB_HOST} -P ${DB_PORT} -D ${DB_DATABASE} -e "DELETE FROM weather_info WHERE timestamp < NOW() - INTERVAL 1 HOUR;"
  echo "$(date): Deleted old weather data successfully" >> cron-job/delete_old_weather_data.log
  sleep 1h
done