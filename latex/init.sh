#!/bin/bash

case $1 in
  start)
    /home/baldr00/latex/Makelatex.sh "$(pwd)/main" &
    echo $! > ./pid
    ;;
  stop)
    kill -s SIGUSR1 $(cat ./pid)
    rm "./pid" 2>/dev/nul
    ;;
  *)
  ;;
  esac

exit 0
