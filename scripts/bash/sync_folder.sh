
SRC_DISK=HDD
DEST_DISK=$1
FOLDER_SYNC=$2

if [ -z "$1" ] || [ -z "$2" ]; then
  echo "usage:"
  echo "$0 destination_disk folder_to_be_sync"
  echo "example:"
  echo "$0 W2R Music"
  echo "$0 W2S Pictures"
  exit
else
  echo "SRC_DISK=$SRC_DISK, DEST_DISK=$DEST_DISK, FOLDER_SYNC=$FOLDER_SYNC"
  exit
fi

rsync -Pavz --delete --no-perms --ignore-errors /Volumes/$SRC_DISK/$FOLDER_SYNC/ /Volumes/$DEST_DISK/$FOLDER_SYNC/ --exclude "ZZZ"
