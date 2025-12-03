DESCRIPTION = "udev rules for Raspberry Pi Boards"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://backlight.rules \
	file://81-backlight.rules \
	file://80-can0.rules \
	"

do_install:append () {
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 0644 ${WORKDIR}/backlight.rules ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${WORKDIR}/81-backlight.rules ${D}${sysconfdir}/udev/rules.d/
    install -m 0644 ${WORKDIR}/80-can0.rules ${D}${sysconfdir}/udev/rules.d/
}
