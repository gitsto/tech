#!/usr/bin/env bash

echo "Quel est le nom du script ? "
read FNAME

if [ -f "$FNAME" ]; then
    echo "Le fichier existe, je m'arrête" >&2
    exit 2
fi

true > "$FNAME"

echo "Quel est le shell à utiliser ? "
read SHL

cat <<  END > "$FNAME"
#!/usr/bin/env $SHL
END
