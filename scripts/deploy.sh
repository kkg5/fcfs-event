#!/bin/bash
DEPLOY_PATH=/home/ubuntu/fcfs-event/deploy
LOG_PATH=$DEPLOY_PATH/deploy.log
LOG_ERROR_PATH=$DEPLOY_PATH/deploy_err.log

BUILD_JAR=$(ls $DEPLOY_PATH/build/libs/fcfs-event-0.0.1.jar)
JAR_NAME=$(basename "$BUILD_JAR")

echo "> 현재 시간: $(date)";
echo "> build 파일명: $JAR_NAME";
echo "> build 파일 복사" >> $LOG_PATH

cp "$BUILD_JAR" "$DEPLOY_PATH"

echo "> 현재 실행중인 애플리케이션 pid 확인" >> $LOG_PATH
CURRENT_PID=$(pgrep -f "$JAR_NAME")

if [ -z "$CURRENT_PID" ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> $LOG_PATH
else
  echo "> kill -9 $CURRENT_PID" >> $LOG_PATH
  sudo kill -9 "$CURRENT_PID"
  sleep 5
fi

DEPLOY_JAR=$DEPLOY_PATH/$JAR_NAME
source /etc/profile
echo "> DEPLOY_JAR 배포"    >> $LOG_PATH
nohup java -jar "$DEPLOY_JAR" --spring.profiles.active=server >> $LOG_PATH 2> $LOG_ERROR_PATH &
