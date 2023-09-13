#!/bin/bash

TEMP=$(getopt -o a:b: --long alpha:,beta: -n 'example.sh' -- "$@")

if [ $? != 0 ]; then
    echo "Terminating..." >&2
    exit 1
fi

set -xv
# Notez que l'utilisation de "eval set --" est nécessaire avec "getopt".
eval set -- "$TEMP"
while true; do
    case "$1" in
        -a|--alpha)
            case "$2" in
                "") shift 2 ;;
                *) ALPHA=$2; shift 2 ;;
            esac ;;
        -b|--beta)
            case "$2" in
                "") shift 2 ;;
                *) BETA=$2; shift 2 ;;
            esac ;;
        --) shift; break ;;
        *) echo "Internal error!"; exit 1 ;;
    esac
done

echo "ALPHA = $ALPHA"
echo "BETA = $BETA"

