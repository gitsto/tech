FOLDER=$1

if [ -z "$1" ]; then
  echo "usage:"
  echo "$0 folder_path"
  echo "example:"
  echo "$0 /Volumes/HDD/Music/"
  FOLDER=/Volumes/HDD/Music/
else
  FOLDER=$1
fi

echo "Run script on FOLDER=$FOLDER"

exit

find $FOLDER -name "._*" -exec rm {} \;
find $FOLDER -name ".DS_Store*" -exec rm {} \;
