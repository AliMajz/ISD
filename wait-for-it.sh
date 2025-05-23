#!/usr/bin/env bash
# wait-for-it.sh from https://github.com/vishnubob/wait-for-it

host="$1"
port="$2"
shift 2
cmd="$@"

until nc -z "$host" "$port"; do
  echo "Waiting for $host:$port..."
  sleep 2
done

exec $cmd