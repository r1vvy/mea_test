set -e

while [ true ]
do
  mysql -u ${DB_USERNAME} -p${DB_PASSWORD} -h ${DB_HOST} -P ${DB_PORT} -e "DELETE FROM weather_data WHERE timestamp < NOW() - INTERVAL 1 HOUR;"
  sleep 3600
done