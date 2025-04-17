#!/bin/sh
# RAUC post-install hook: Kopiert die WLAN-Konfiguration in den neuen Slot

set -e

echo "[HOOK] Starte copy-wpa-hook.sh (Phase: $1, Slot: $3)" >&2

PHASE="$1"
BUNDLE="$2"
SLOT="$3"

if [ "$PHASE" != "post-install" ]; then
    echo "[HOOK] Phase ist nicht post-install. Beende." >&2
    exit 0
fi

SOURCE_CONF="/etc/wpa_supplicant/wpa_supplicant@wlan0.conf"
TARGET_MOUNT="/mnt/rauc-target"
TARGET_CONF_DIR="$TARGET_MOUNT/etc/wpa_supplicant"
TARGET_DEVICE="/dev/disk/by-partlabel/$SLOT"

# Prüfen, ob Konfigurationsdatei existiert
if [ ! -f "$SOURCE_CONF" ]; then
    echo "[HOOK] WARNUNG: $SOURCE_CONF nicht gefunden. Abbruch." >&2
    exit 1
fi

# Zielpartition mounten
echo "[HOOK] Mounten von $TARGET_DEVICE nach $TARGET_MOUNT..." >&2
mkdir -p "$TARGET_MOUNT"
mount "$TARGET_DEVICE" "$TARGET_MOUNT"

# Zielverzeichnis vorbereiten
mkdir -p "$TARGET_CONF_DIR"
cp "$SOURCE_CONF" "$TARGET_CONF_DIR/"

echo "[HOOK] Datei erfolgreich kopiert: $SOURCE_CONF -> $TARGET_CONF_DIR/" >&2

# Aufräumen
umount "$TARGET_MOUNT"
echo "[HOOK] Unmount abgeschlossen. Hook erfolgreich." >&2

exit 0

