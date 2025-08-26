SUMMARY = "Installiert Udev Backlight Rules für RPi4 und RPi5"
LICENSE = "CLOSED"

# Nur für die beiden Maschinen bauen
COMPATIBLE_MACHINE = "(ov-rpi4-64|ov-rpi5)"

SRC_URI = " \
    file://backlight.rules \
    file://81-backlight.rules \
"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/backlight.rules ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${WORKDIR}/81-backlight.rules ${D}${sysconfdir}/udev/rules.d/
}

FILES:${PN} += "${sysconfdir}/udev/rules.d/*.rules"

