FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:rpi = " \
    file://fw_env.config \
    file://deactivate-console.cfg \
"
SRC_URI:append:raspberrypi4 = " file://maxsize.cfg"

DEPENDS:append:rpi = " u-boot-default-script"

do_install:append:rpi () {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/fw_env.config
}
